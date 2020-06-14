package com.example.myhealth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameHos;
    private String city;
    private String typeOfSur;
    private String phoneH;


    @ManyToMany
    private Set<Doctor> doctors = new HashSet<>();


    public Hospital() {
    }

    public Hospital(String nameHos, String city, String typeOfSur, String phone) {
        this.nameHos = nameHos;
        this.city = city;
        this.typeOfSur = typeOfSur;
        this.phoneH = phone;

    }

}