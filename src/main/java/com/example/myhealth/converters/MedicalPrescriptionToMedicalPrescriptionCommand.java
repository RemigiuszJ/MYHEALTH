package com.example.myhealth.converters;
import com.example.myhealth.commands.MedicalPrescriptionCommand;
import com.example.myhealth.model.MedicalPrescription;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Component
public class MedicalPrescriptionToMedicalPrescriptionCommand implements Converter<MedicalPrescription, MedicalPrescriptionCommand> {
    @Synchronized
    @Nullable
    @Override
    public MedicalPrescriptionCommand convert(MedicalPrescription source) {
        if (source == null) {
            return null;
        }
        final MedicalPrescriptionCommand medicalPrescriptionCommand = new MedicalPrescriptionCommand();
        medicalPrescriptionCommand.setId(source.getId());
        medicalPrescriptionCommand.setNamed(source.getNamed());
        medicalPrescriptionCommand.setDateOfIssue(source.getDateOfIssue());
        medicalPrescriptionCommand.setPickUpdate(source.getPickUpdate());
        medicalPrescriptionCommand.setPharmacyName(source.getPharmacyName());
        return medicalPrescriptionCommand;
    }



}
