package com.example.myhealth.converters;
import com.example.myhealth.commands.HospitalCommand;
import com.example.myhealth.model.Hospital;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Component
public class HospitalToHospitalCommand implements Converter<Hospital, HospitalCommand> {
    @Synchronized
    @Nullable
    @Override
    public HospitalCommand convert(Hospital source) {
        if (source == null) {
            return null;
        }
        final HospitalCommand hospitalCommand = new HospitalCommand();
        hospitalCommand.setId(source.getId());
        hospitalCommand.setNameHos(source.getNameHos());
        hospitalCommand.setCity(source.getCity());
        hospitalCommand.setPhoneH(source.getPhoneH());
        return hospitalCommand;
    }



}