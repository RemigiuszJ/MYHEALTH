package com.example.myhealth.controllers;

import com.example.myhealth.commands.DoctorCommand;
import com.example.myhealth.commands.HospitalCommand;
import com.example.myhealth.commands.PillsCommand;
import com.example.myhealth.converters.*;
import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.Hospital;
import com.example.myhealth.model.Pills;
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
public class DoctorController {
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


    public DoctorController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, MedicalPrescriptionRepository medicalPrescriptionRepository, PillsRepository pillsRepository, DoctorCommandToDoctor doctorCommandToDoctor, HospitalCommandToHospital hospitalCommandToHospital, MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription, PillsCommandToPills pillsCommandToPills, MedicalPrescriptionToMedicalPrescriptionCommand prescriptionToMedicalPrescriptionCommand, PillsToPillsCommand pillsToPillsCommand, DoctorToDoctorCommand doctorToDoctorCommand, HospitalToHospitalCommand hospitalToHospitalCommand) {
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

    @RequestMapping(value = {"/doctors", "/doctor/list"})
    public String getDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctor/list";
    }

    @RequestMapping("/doctor/{id}/hospital")
    public String getDoctorsHospitals(Model model, @PathVariable("id") Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        if (doctor.isPresent()) {
            model.addAttribute("hospitals", hospitalRepository.getAllByDoctorsContaining(doctor.get()));
            model.addAttribute("filter", "doctor: " + doctor.get().getName() + " " + doctor.get().getLastName());
        } else {
            model.addAttribute("hospital", new ArrayList<>());
            model.addAttribute("filter", "doctor for this id doesn't exist");
        }

        return "hospital/list";
    }

    @RequestMapping("/doctor/{id}/show")
    public String getDoctorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("doctor", doctorRepository.findById(id).get());
        return "doctor/show";
    }

    @RequestMapping("/doctor/{id}/edit")
    public String editDoctor(Model model, @PathVariable("id") Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        DoctorCommand doctorCommand = doctorToDoctorCommand.convert(doctor);
        model.addAttribute("doctor", doctorCommand);
        return "doctor/addedit";
    }

    @RequestMapping("/doctor/{id}/delete")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping
    @RequestMapping("/doctor/new")
    public String newDoctor(Model model){
        model.addAttribute("doctor", new DoctorCommand());
        model.addAttribute("hospitals", hospitalRepository.findAll());
        return "doctor/addedit";
    }

    @PostMapping("doctor")
    public String saveOrUpdate(@ModelAttribute DoctorCommand command){
       Doctor detachedDoctor = doctorCommandToDoctor.convert(command);
       Doctor savedDoctor = doctorRepository.save(detachedDoctor);

        return "redirect:/doctor/" + savedDoctor.getId() + "/show";
    }
}