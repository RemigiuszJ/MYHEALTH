package com.example.myhealth.tools;

import com.example.myhealth.model.Doctor;
import com.example.myhealth.model.Hospital;
import com.example.myhealth.model.MedicalPrescription;
import com.example.myhealth.model.Pills;
import com.example.myhealth.repositories.DoctorRepository;
import com.example.myhealth.repositories.HospitalRepository;
import com.example.myhealth.repositories.MedicalPrescriptionRepository;
import com.example.myhealth.repositories.PillsRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.print.Doc;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {


    public DBInflater(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, MedicalPrescriptionRepository medicalPrescriptionRepository, PillsRepository pillsRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.medicalPrescriptionRepository = medicalPrescriptionRepository;
        this.pillsRepository = pillsRepository;
    }
    private DoctorRepository doctorRepository;
    private HospitalRepository hospitalRepository;
    private MedicalPrescriptionRepository medicalPrescriptionRepository;
    private PillsRepository pillsRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
    }
    private void initData() {

        Doctor natalia = new Doctor("Natalia","Słabiak-Błaź", "nefrolog", "332591411");
        Hospital klinikaKatowice = new Hospital("Klinika odział 1", "Katowice", "przeszczep", "322591400");
        MedicalPrescription pierwsza = new MedicalPrescription("YES","8.06.2020","10.06.2020","Banotówka");
        Pills envarsus = new Pills("Envarsus","1 x 2.25mg","9:00","15.10.2020");



        natalia.getHospitals().add(klinikaKatowice);
        klinikaKatowice.getDoctors().add(natalia);
        pierwsza.getPillsHashSet().add(envarsus);
        envarsus.getMedicalPrescriptions().add(pierwsza);
        doctorRepository.save(natalia);
        hospitalRepository.save(klinikaKatowice);
        pillsRepository.save(envarsus);
        medicalPrescriptionRepository.save(pierwsza);

        Doctor kuczera = new Doctor("Piotr","Kuczera", "nefrolog", "332591400");
        Hospital nefrologiaKatowice = new Hospital("Klinika odział 2", "Katowice", "przeszczep", "322591400");
        MedicalPrescription druga = new MedicalPrescription("NO","10.06.2020","15.06.2020","Liburnia");
        Pills doxar = new Pills("Doxar","1 x 3.25mg"," 6:00","15.10.2020");

        druga.getPillsHashSet().add(doxar);
        kuczera.getHospitals().add(nefrologiaKatowice);
        doxar.getMedicalPrescriptions().add(druga);
        nefrologiaKatowice.getDoctors().add(kuczera);
        doctorRepository.save(kuczera);
        hospitalRepository.save(nefrologiaKatowice);
        pillsRepository.save(doxar);
        medicalPrescriptionRepository.save(druga);

        Doctor wystrychowski = new Doctor("Zygmunt","Wystrychowski", "transplantolog", "332591529");
        Hospital transplatogiaKlinika = new Hospital("Klinika im. Andrzeja Mielęckiego", "Katowice", "przeszczep", "322591400");
        MedicalPrescription trzecia = new MedicalPrescription("No","15.06.2020","20.06.2020","Liburnia");
        Pills mycofit = new Pills("Mycofit","2x 500mg","rano 6:00","15.10.2020");

        transplatogiaKlinika.getDoctors().add(wystrychowski);
        trzecia.getPillsHashSet().add(mycofit);
        wystrychowski.getHospitals().add(transplatogiaKlinika);
        mycofit.getMedicalPrescriptions().add(trzecia);
        doctorRepository.save(wystrychowski);
        hospitalRepository.save(transplatogiaKlinika);
        pillsRepository.save(mycofit);
        medicalPrescriptionRepository.save(trzecia);

    }
}
