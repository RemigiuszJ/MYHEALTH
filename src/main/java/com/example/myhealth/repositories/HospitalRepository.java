package com.example.myhealth.repositories;

import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.Hospital;
import org.springframework.data.repository.CrudRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    Optional<Hospital> getFirstByNameHosAndTypeOfSur(String NameHos, String TypeOfSur);
    Object getAllByDoctorsContaining(Doctor doctor);
}
