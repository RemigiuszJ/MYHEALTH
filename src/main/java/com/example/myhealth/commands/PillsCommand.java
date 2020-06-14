package com.example.myhealth.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PillsCommand {
    private Long id;
    private String namePills;
    private String dosage;
    private String expirationDate;
    private String takeTime;
    private Long medicalPrescriptionId;
}
