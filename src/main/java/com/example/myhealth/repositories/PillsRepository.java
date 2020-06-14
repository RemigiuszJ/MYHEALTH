package com.example.myhealth.repositories;

import com.example.myhealth.model.Pills;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PillsRepository extends CrudRepository<Pills, Long> {
    Optional<Pills> getFirstByNamePillsAndAndDosage(String namePills, String dosage);
}
