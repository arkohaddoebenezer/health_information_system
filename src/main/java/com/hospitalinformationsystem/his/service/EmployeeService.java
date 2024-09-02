package com.hospitalinformationsystem.his.service;

import com.hospitalinformationsystem.his.model.*;
import com.hospitalinformationsystem.his.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final HospitalizationRepository hospitalizationRepository;
    private final DepartmentRepository departmentRepository;
    private final WardRepository wardRepository;
    private final Logger logger =Logger.getLogger(this.getClass().getName());

    @Autowired
    public EmployeeService(DoctorRepository doctorRepository, NurseRepository nurseRepository, HospitalizationRepository hospitalizationRepository, DepartmentRepository departmentRepository, WardRepository wardRepository) {
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.hospitalizationRepository = hospitalizationRepository;
        this.departmentRepository = departmentRepository;
        this.wardRepository = wardRepository;
    }


    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(doctorRepository.findAll());
        employees.addAll(nurseRepository.findAll());
        return employees;
    }
    @Cacheable(cacheNames="employee", key="#employeeNumber")
    public Optional<Employee> findEmployee(String employeeNumber) {
        logger.info("get Employee from database: employeeNumber="+employeeNumber);
        Optional<Doctor> doctor = doctorRepository.findByEmployeeNumber(employeeNumber);
        if (doctor.isPresent()) return Optional.of(doctor.get());

        Optional<Nurse> nurse = nurseRepository.findById(employeeNumber);
        if (nurse.isPresent()) return Optional.of(nurse.get());
        return Optional.empty();
    }

    public List<Employee> findEmployeesBySurname(String surname) {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(doctorRepository.findBySurname(surname));
        employees.addAll(nurseRepository.findBySurname(surname));
        return employees;
    }

    @Transactional
    @CacheEvict(value = "employee",key = "employeeNumber")
    public void deleteEmployeeByEmployeeNumber(String employeeNumber) {

        if (doctorRepository.existsByEmployeeNumber(employeeNumber)) {
            Optional<Doctor> doctor = doctorRepository.findByEmployeeNumber(employeeNumber);
            List<Hospitalization> relatedHospitalizations = hospitalizationRepository.findByDoctor(doctor);
            for (Hospitalization hospitalization : relatedHospitalizations) {
                hospitalization.setDoctor(null);
                hospitalizationRepository.save(hospitalization);
            }
            Department departmentDirectedByDoctor = departmentRepository.findByDirector(doctor);
            departmentDirectedByDoctor.setDirector(null);
            departmentRepository.save(departmentDirectedByDoctor);
            doctorRepository.deleteByEmployeeNumber(employeeNumber);
        } else if (nurseRepository.existsByEmployeeNumber(employeeNumber)) {
            Optional<Nurse> nurse = nurseRepository.findByEmployeeNumber(employeeNumber);
            Ward wardSupervisedByNurse= wardRepository.findBySupervisor(nurse);
            wardSupervisedByNurse.setSupervisor(null);
            wardRepository.save(wardSupervisedByNurse);
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
