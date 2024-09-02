package com.hospitalinformationsystem.his.service;

import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Employee;
import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.repository.DoctorRepository;
import com.hospitalinformationsystem.his.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    @Autowired
    public EmployeeService(DoctorRepository doctorRepository, NurseRepository nurseRepository) {
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(doctorRepository.findAll());
        employees.addAll(nurseRepository.findAll());
        return employees;
    }

    public Optional<Employee> findEmployeeByEmployeeNumber(String employeeNumber) {
        System.out.println("Find employee by employee number: " + employeeNumber);
        Optional<Doctor> doctor = doctorRepository.findById(employeeNumber);
        if (doctor.isPresent()) return Optional.of(doctor.get());

        Optional<Nurse> nurse = nurseRepository.findById(employeeNumber);
        if (nurse.isPresent()) return Optional.of(nurse.get());
        return Optional.empty();
    }

    public List<Employee> findEmployeesBySurname(String lastName) {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(doctorRepository.findBySurname(lastName));
        employees.addAll(nurseRepository.findBySurname(lastName));
        return employees;
    }

    @Transactional
    public void deleteEmployeeByEmployeeNumber(String employeeNumber) {
        if (doctorRepository.existsByEmployeeNumber(employeeNumber)) {
            System.out.println("Doctor exists");
            doctorRepository.deleteByEmployeeNumber(employeeNumber);
        } else if (nurseRepository.existsByEmployeeNumber(employeeNumber)) {
            System.out.println("Nurse exists");
            nurseRepository.deleteByEmployeeNumber(employeeNumber);
        }
    }

    public List<Doctor> findDoctorsBySpecialization(String specialization) {
        return doctorRepository.findDoctorBySpeciality(specialization);
    }

    public Doctor saveDoctor(Doctor doctor) {
        System.out.println("Save/Update Doctor");
        return doctorRepository.save(doctor);
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }
}
