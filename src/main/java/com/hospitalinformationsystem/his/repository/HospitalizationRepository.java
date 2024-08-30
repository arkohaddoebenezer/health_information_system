package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalizationRepository extends JpaRepository<Hospitalization, Integer> {
}
