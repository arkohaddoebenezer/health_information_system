package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, String> {

    Ward findBySupervisor(Optional<Nurse> nurse);

}
