package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Hospitalization;
import com.hospitalinformationsystem.his.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalizationRepository extends JpaRepository<Hospitalization, Integer> {


    List<Hospitalization> findByDoctor(Optional<Doctor> doctor);
    List<Hospitalization> findByDoctor( Doctor doctor);

    List<Hospitalization> findByPatient(Patient patient);
}
