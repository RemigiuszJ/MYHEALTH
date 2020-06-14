package com.example.myhealth.controllers;

import com.example.myhealth.commands.PillsCommand;
import com.example.myhealth.converters.*;
import com.example.myhealth.model.Pills;
import com.example.myhealth.repositories.DoctorRepository;
import com.example.myhealth.repositories.HospitalRepository;
import com.example.myhealth.repositories.MedicalPrescriptionRepository;
import com.example.myhealth.repositories.PillsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PillsController {

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

    public PillsController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, MedicalPrescriptionRepository medicalPrescriptionRepository, PillsRepository pillsRepository, DoctorCommandToDoctor doctorCommandToDoctor, HospitalCommandToHospital hospitalCommandToHospital, MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription, PillsCommandToPills pillsCommandToPills, MedicalPrescriptionToMedicalPrescriptionCommand prescriptionToMedicalPrescriptionCommand, PillsToPillsCommand pillsToPillsCommand, DoctorToDoctorCommand doctorToDoctorCommand, HospitalToHospitalCommand hospitalToHospitalCommand) {
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


    @RequestMapping(value = {"/pillsHashSet" , "/pills/list"})
    public String getPillsHashSet(Model model) {
        model.addAttribute("PillsHashSet", pillsRepository.findAll());
        return "pills/list";
    }

    @GetMapping
    @RequestMapping("/pills/{id}/show")
    public String getPillsDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("pills", pillsRepository.findById(id).get());
        return "pills/show";
    }
    @RequestMapping("/pills/{id}/edit")
    public String editPills(Model model, @PathVariable("id") Long id) {
        Pills pills = pillsRepository.findById(id).get();
        PillsCommand pillsCommand = pillsToPillsCommand.convert(pills);
        model.addAttribute("pills",pillsCommand);
        return "pills/addedit";
    }

    @GetMapping
    @RequestMapping("/pills/{id}/delete")
    public String deletePills(@PathVariable("id") Long id) {
        pillsRepository.deleteById(id);
        return "redirect:/pillsHashSet";
    }

    @GetMapping
    @RequestMapping("/pills/new")
    public String newPills(Model model){
        model.addAttribute("pills", new PillsCommand());
        model.addAttribute("medicalPrescriptions", medicalPrescriptionRepository.findAll());
        return "pills/addedit";
    }

    @PostMapping("pills")
    public String saveOrUpdate(@ModelAttribute PillsCommand command){
        Pills detachedPills = pillsCommandToPills.convert(command);
        Pills savedPills = pillsRepository.save(detachedPills);

        return "redirect:/pills/" + savedPills.getId() + "/show";
    }
}
