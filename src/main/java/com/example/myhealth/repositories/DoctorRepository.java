package com.example.myhealth.repositories;

import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.Hospital;
import com.example.myhealth.model.MedicalPrescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Optional<Doctor> getFirstByNameAndLastName(String firstName, String lastName);
    Object getAllByHospitalsContaining(Hospital hospital);
    Object getAllByMedicalPrescriptionsContaining(MedicalPrescription medicalPrescription);
}