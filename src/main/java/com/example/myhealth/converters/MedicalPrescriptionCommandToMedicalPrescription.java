package com.example.myhealth.converters;

import com.example.myhealth.commands.MedicalPrescriptionCommand;
import com.example.myhealth.model.MedicalPrescription;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class MedicalPrescriptionCommandToMedicalPrescription implements Converter<MedicalPrescriptionCommand, MedicalPrescription> {
    @Synchronized
    @Nullable
    @Override
    public MedicalPrescription convert(MedicalPrescriptionCommand source){
        if(source == null){
            return null;
        }
        final MedicalPrescription medicalPrescription = new MedicalPrescription();
        medicalPrescription.setNamed(source.getNamed());
        medicalPrescription.setDateOfIssue(source.getDateOfIssue());
        medicalPrescription.setPickUpdate(source.getPickUpdate());
        medicalPrescription.setPharmacyName(source.getPharmacyName());
        return medicalPrescription;

    }

}
