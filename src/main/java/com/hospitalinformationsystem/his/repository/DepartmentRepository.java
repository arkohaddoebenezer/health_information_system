package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
