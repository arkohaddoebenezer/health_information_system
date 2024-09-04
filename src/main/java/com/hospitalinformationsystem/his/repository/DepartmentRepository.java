package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Department;
import com.hospitalinformationsystem.his.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDirector(Doctor doctor);
    Department findByDirector(Optional<Doctor> doctor);
}
