package com.example.myhealth.converters;

import com.example.myhealth.commands.PillsCommand;
import com.example.myhealth.model.Pills;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PillsCommandToPills implements Converter<PillsCommand, Pills> {


    @Synchronized
    @Nullable
    @Override
    public Pills convert(PillsCommand source){
        if(source == null) {
            return null;
        }
        final Pills pills = new Pills();
        pills.setNamePills(source.getNamePills());
        pills.setDosage(source.getDosage());
        pills.setTakeTime(source.getTakeTime());
        pills.setExpirationDate(source.getExpirationDate());
        return pills;


    }
}
