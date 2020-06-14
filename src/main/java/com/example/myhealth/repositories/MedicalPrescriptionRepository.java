package com.example.myhealth.repositories;

import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.MedicalPrescription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicalPrescriptionRepository extends CrudRepository<MedicalPrescription, Long> {
    Optional<MedicalPrescription> getFirstByNamedAndPharmacyName(String named, String pharmacyName);
    Object getByDoctorsContaining(Doctor doctor);
}
