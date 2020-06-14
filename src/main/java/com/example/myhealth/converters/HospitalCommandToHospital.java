package com.example.myhealth.converters;

import com.example.myhealth.commands.HospitalCommand;
import com.example.myhealth.model.Hospital;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Component
public class HospitalCommandToHospital implements Converter<HospitalCommand, Hospital> {
    @Synchronized
    @Nullable
    @Override
    public Hospital convert(HospitalCommand source) {
        if (source == null) {
            return null;
        }
        final Hospital hospital = new Hospital();
        hospital.setNameHos(source.getNameHos());
        hospital.setCity(source.getCity());
        hospital.setTypeOfSur(source.getTypeOfSur());
        hospital.setPhoneH(source.getPhoneH());
        return hospital;
    }
}

