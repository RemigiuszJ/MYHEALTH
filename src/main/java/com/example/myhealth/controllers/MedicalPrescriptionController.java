package com.example.myhealth.controllers;


import com.example.myhealth.commands.DoctorCommand;
import com.example.myhealth.commands.MedicalPrescriptionCommand;
import com.example.myhealth.converters.*;
import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.MedicalPrescription;
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
public class MedicalPrescriptionController {
    private DoctorRepository doctorRepository;
    private HospitalRepository hospitalRepository;
    private MedicalPrescriptionRepository medicalPrescriptionRepository;
    private PillsRepository pillsRepository;
    private DoctorCommandToDoctor doctorCommandToDoctor;
    private HospitalCommandToHospital hospitalCommandToHospital;
    private MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription;
    private PillsCommandToPills pillsCommandToPills;
    private MedicalPrescriptionToMedicalPrescriptionCommand medicalPrescriptionToMedicalPrescriptionCommand;
    private PillsToPillsCommand pillsToPillsCommand;
    private DoctorToDoctorCommand doctorToDoctorCommand;
    private HospitalToHospitalCommand hospitalToHospitalCommand;

    public MedicalPrescriptionController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, MedicalPrescriptionRepository medicalPrescriptionRepository, PillsRepository pillsRepository, DoctorCommandToDoctor doctorCommandToDoctor, HospitalCommandToHospital hospitalCommandToHospital, MedicalPrescriptionCommandToMedicalPrescription medicalPrescriptionCommandToMedicalPrescription, PillsCommandToPills pillsCommandToPills, MedicalPrescriptionToMedicalPrescriptionCommand medicalPrescriptionToMedicalPrescriptionCommand, PillsToPillsCommand pillsToPillsCommand, DoctorToDoctorCommand doctorToDoctorCommand, HospitalToHospitalCommand hospitalToHospitalCommand) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.medicalPrescriptionRepository = medicalPrescriptionRepository;
        this.pillsRepository = pillsRepository;
        this.doctorCommandToDoctor = doctorCommandToDoctor;
        this.hospitalCommandToHospital = hospitalCommandToHospital;
        this.medicalPrescriptionCommandToMedicalPrescription = medicalPrescriptionCommandToMedicalPrescription;
        this.pillsCommandToPills = pillsCommandToPills;
        this.medicalPrescriptionToMedicalPrescriptionCommand = medicalPrescriptionToMedicalPrescriptionCommand;
        this.pillsToPillsCommand = pillsToPillsCommand;
        this.doctorToDoctorCommand = doctorToDoctorCommand;
        this.hospitalToHospitalCommand = hospitalToHospitalCommand;
    }

    @RequestMapping(value = {"/medicalPrescriptions", "/medicalPrescription/list"})
    public String getMedicalPrescriptions(Model model) {
        model.addAttribute("medicalPrescriptions", medicalPrescriptionRepository.findAll());
        return "medicalPrescription/list";
    }
    @GetMapping
    @RequestMapping("/medicalPrescription/{id}/show")
    public String getMedicalPrescriptionDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("medicalPrescription", medicalPrescriptionRepository.findById(id).get());
        return "medicalPrescription/show";
    }

    @RequestMapping("/medicalPrescription/{id}/edit")
    public String editMedicalPrescription(Model model, @PathVariable("id") Long id) {
        MedicalPrescription medicalPrescription = medicalPrescriptionRepository.findById(id).get();
        MedicalPrescriptionCommand medicalPrescriptionCommand = medicalPrescriptionToMedicalPrescriptionCommand.convert(medicalPrescription);
        model.addAttribute("medicalPrescription", medicalPrescriptionCommand);
        return "medicalPrescription/addedit";
    }
    @GetMapping
    @RequestMapping("/medicalPrescription/{id}/delete")
    public String deleteMedicalPrescription(@PathVariable("id") Long id) {
        medicalPrescriptionRepository.deleteById(id);
        return "redirect:/medicalPrescriptions";
    }

    @GetMapping
    @RequestMapping("/medicalPrescription/new")
    public String newMedicalPrescription(Model model){
        model.addAttribute("medicalPrescription", new MedicalPrescriptionCommand());
        model.addAttribute("pillsSet", pillsRepository.findAll());
        return "medicalPrescription/addedit";
    }

    @PostMapping("medicalPrescription")
    public String saveOrUpdate(@ModelAttribute MedicalPrescriptionCommand command){
        MedicalPrescription detachedMedicalPrescription = medicalPrescriptionCommandToMedicalPrescription.convert(command);
        MedicalPrescription savedMedicalPrescription = medicalPrescriptionRepository.save(detachedMedicalPrescription);

        return "redirect:/medicalPrescription/" + savedMedicalPrescription.getId() + "/show";
    }
}



