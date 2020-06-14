package com.example.myhealth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class MedicalPrescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String named;
    private String dateOfIssue;
    private String pickUpdate;
    private String pharmacyName;


    @ManyToMany
    private Set<Doctor> doctors = new HashSet<>();

    @ManyToMany
     private Set<Pills> pillsHashSet = new HashSet<>();

    public MedicalPrescription() {
    }

    public MedicalPrescription( String named, String dateOfIssue, String pickUpdate, String pharmacyName) {
        this.named = named;
        this.dateOfIssue = dateOfIssue;
        this.pickUpdate = pickUpdate;
        this.pharmacyName = pharmacyName;
    }

}