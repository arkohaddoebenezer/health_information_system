package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {
}
