package com.example.myhealth.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HospitalCommand {
    private  Long id;
    private String nameHos;
    private String city;
    private String typeOfSur;
    private String phoneH;
    private Long doctorId;
}
