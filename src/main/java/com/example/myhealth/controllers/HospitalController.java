package com.example.myhealth.controllers;


import com.example.myhealth.commands.HospitalCommand;
import com.example.myhealth.converters.*;
import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.Hospital;
import com.example.myhealth.repositories.DoctorRepository;
import com.example.myhealth.repositories.HospitalRepository;
import com.example.myhealth.repositories.MedicalPrescriptionRepository;
import com.example.myhealth.repositories.PillsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HospitalController {
    private DoctorRepository doctorRepository;
    private HospitalRepository hospitalRepository;
    private MedicalPrescriptionRepository medicalPrescriptionRepository;
    private PillsRepository pillsRepository;
    private DoctorCommandToDoctor doctorCommandToDoctor;
    private HospitalCommandToHospital hospitalCommandToHospital;
    private MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription;
    private PillsCommandToPills pillsCommandToPills;
    private MedicalPrescriptionToMedicalPrescriptionCommand prescriptionToMedicalPrescriptionCommand;
    private PillsToPillsCommand pillsToPillsCommand;
    private DoctorToDoctorCommand doctorToDoctorCommand;
    private HospitalToHospitalCommand hospitalToHospitalCommand;


    public HospitalController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, MedicalPrescriptionRepository medicalPrescriptionRepository, PillsRepository pillsRepository, DoctorCommandToDoctor doctorCommandToDoctor, HospitalCommandToHospital hospitalCommandToHospital, MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription, PillsCommandToPills pillsCommandToPills, MedicalPrescriptionToMedicalPrescriptionCommand prescriptionToMedicalPrescriptionCommand, PillsToPillsCommand pillsToPillsCommand, DoctorToDoctorCommand doctorToDoctorCommand, HospitalToHospitalCommand hospitalToHospitalCommand) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.medicalPrescriptionRepository = medicalPrescriptionRepository;
        this.pillsRepository = pillsRepository;
        this.doctorCommandToDoctor = doctorCommandToDoctor;
        this.hospitalCommandToHospital = hospitalCommandToHospital;
        this.medicalPrescriptionCommandToMedicalPrescription = medicalPrescriptionCommandToMedicalPrescription;
        this.pillsCommandToPills = pillsCommandToPills;
        this.prescriptionToMedicalPrescriptionCommand = prescriptionToMedicalPrescriptionCommand;
        this.pillsToPillsCommand = pillsToPillsCommand;
        this.doctorToDoctorCommand = doctorToDoctorCommand;
        this.hospitalToHospitalCommand = hospitalToHospitalCommand;
    }

    @RequestMapping(value = {"/hospitals", "/hospital/list"})
    public String getHospitals(Model model) {
        model.addAttribute("hospitals", hospitalRepository.findAll());
        return "hospital/list";
    }

    @RequestMapping("/hospital/{id}/doctors")
    public String getHospitalsDoctors(Model model, @PathVariable("id") Long id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        if (hospital.isPresent()) {
            model.addAttribute("doctors", doctorRepository.getAllByHospitalsContaining(hospital.get()));
            model.addAttribute("filter", "hospital: " + hospital.get().getNameHos() + " " +hospital.get().getCity());
        } else {
            model.addAttribute("doctors", new ArrayList<>());
            model.addAttribute("filter", "hospital for this id doesn't exist");
        }

        return "doctor/list";
    }

    @RequestMapping("/hospital/{id}/show")
    public String getHospitalDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hospital", hospitalRepository.findById(id).get());
        return "hospital/show";
    }

    @RequestMapping("/hospital/{id}/edit")
    public String editHospital(Model model, @PathVariable("id") Long id) {
        Hospital hospital = hospitalRepository.findById(id).get();
       HospitalCommand hospitalCommand = hospitalToHospitalCommand.convert(hospital);
        model.addAttribute("hospital", hospitalCommand);
        return "hospital/addedit";
    }

    @RequestMapping("/hospital/{id}/delete")
    public String deleteHospital(@PathVariable("id") Long id) {
      hospitalRepository.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping
    @RequestMapping("/hospital/new")
    public String newDoctor(Model model){
        model.addAttribute("hospital", new HospitalCommand());
        return "hospital/addedit";
    }

    @PostMapping("hospital")
    public String saveOrUpdate(@ModelAttribute HospitalCommand command){

        if (command.getId() > 0) {
            Hospital hospitalToUpdate = hospitalRepository.findById(command.getId()).get();
            hospitalToUpdate.setNameHos(command.getNameHos());
            hospitalToUpdate.setCity(command.getCity());
            hospitalToUpdate.setTypeOfSur(command.getTypeOfSur());
            hospitalToUpdate.setPhoneH(command.getPhoneH());
            hospitalRepository.save(hospitalToUpdate);
        }

        Optional<Hospital>  hospitalOptional = hospitalRepository.getFirstByNameHosAndTypeOfSur(command.getNameHos(), command.getTypeOfSur());

        if (!hospitalOptional.isPresent()) {
            Hospital detachedHospital = hospitalCommandToHospital.convert(command);
            Hospital savedHospital = hospitalRepository.save(detachedHospital);
            return "redirect:/hospital/" + savedHospital.getId() + "/show";
        } else {

            System.out.println("  error ");
            return "redirect:/hospital/" + hospitalOptional.get().getId() + "/show";
        }
    }
}