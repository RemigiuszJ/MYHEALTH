package com.example.myhealth.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DoctorCommand {
    private Long id;
    private String name;
    private String lastName;
    private  String spec;
    private String phoned;
    private Long hospitalId;
}
