package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.Doctor;

import java.util.List;

public interface DoctorRepository extends EmployeeRepository<Doctor>  {
    List<Doctor> findDoctorBySpeciality (String specialization);
}
