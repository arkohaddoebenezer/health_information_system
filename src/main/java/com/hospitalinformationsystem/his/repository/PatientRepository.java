package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
