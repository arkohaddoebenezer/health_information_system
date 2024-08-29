package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
