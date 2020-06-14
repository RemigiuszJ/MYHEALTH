package com.example.myhealth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Pills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String namePills;
    private String dosage;
    private String takeTime;
    private String expirationDate;

   @ManyToMany(mappedBy = "pillsHashSet")
   private Set<MedicalPrescription> medicalPrescriptions = new HashSet<>();

    public Pills() {
    }

    public Pills(String namePills, String dosage, String takeTime, String expirationDate) {
        this.namePills = namePills;
        this.dosage = dosage;
        this.takeTime = takeTime;
        this.expirationDate = expirationDate;
    }

}