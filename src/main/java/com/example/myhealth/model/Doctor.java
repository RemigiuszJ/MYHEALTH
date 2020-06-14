package com.example.myhealth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String spec;
    private String phoned;


    @ManyToMany(mappedBy = "doctors")
    private Set<Hospital> hospitals = new HashSet<>();

    @ManyToMany(mappedBy = "doctors")
    private Set<MedicalPrescription> medicalPrescriptions = new HashSet<>();

    public Doctor() {
    }

    public Doctor(String name, String lastName, String spec, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.spec = spec;
        this.phoned = phone;

    }



}