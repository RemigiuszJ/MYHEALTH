package com.example.myhealth.converters;

import com.example.myhealth.commands.PillsCommand;
import com.example.myhealth.model.Hospital;
import com.example.myhealth.model.Pills;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
@Component
public class PillsToPillsCommand implements Converter<Pills, PillsCommand> {
    @Synchronized
    @Nullable
    @Override
    public PillsCommand convert(Pills source) {
        if (source == null) {
            return null;
        }
        final PillsCommand pillsCommand = new PillsCommand();
        pillsCommand.setId(source.getId());
        pillsCommand.setNamePills(source.getNamePills());
        pillsCommand.setDosage(source.getDosage());
        pillsCommand.setTakeTime(source.getTakeTime());
        pillsCommand.setExpirationDate(source.getExpirationDate());

        return pillsCommand;
    }



}