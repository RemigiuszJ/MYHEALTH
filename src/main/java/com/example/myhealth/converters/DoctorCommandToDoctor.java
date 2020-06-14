package com.example.myhealth.converters;

import com.example.myhealth.commands.DoctorCommand;
import com.example.myhealth.model.Doctor;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class DoctorCommandToDoctor implements Converter<DoctorCommand, Doctor> {
    @Synchronized
    @Nullable
    @Override
    public Doctor convert(DoctorCommand source) {
        if (source == null) {
            return null;
        }
        final Doctor doctor = new Doctor();
        doctor.setName(source.getName());
        doctor.setLastName(source.getLastName());
        doctor.setSpec(source.getSpec());
        doctor.setPhoned(source.getPhoned());
        return doctor;
        }

}




