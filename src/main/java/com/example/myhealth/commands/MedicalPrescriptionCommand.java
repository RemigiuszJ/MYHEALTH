package com.example.myhealth.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalPrescriptionCommand {
    private Long id;
    private String named;
    private String dateOfIssue;
    private String pickUpdate;
    private String pharmacyName;
    private Long pillsId;
}
