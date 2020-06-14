package com.example.myhealth.converters;
import com.example.myhealth.commands.DoctorCommand;
import com.example.myhealth.model.Doctor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Component
public class DoctorToDoctorCommand implements Converter<Doctor, DoctorCommand> {
    @Synchronized
    @Nullable
    @Override
    public DoctorCommand convert(Doctor source) {
        if (source == null) {
            return null;
        }
        final DoctorCommand doctorCommand = new DoctorCommand();
        doctorCommand.setId(source.getId());
        doctorCommand.setName(source.getName());
        doctorCommand.setLastName(source.getLastName());
        doctorCommand.setSpec(source.getSpec());
        return doctorCommand;
    }
}