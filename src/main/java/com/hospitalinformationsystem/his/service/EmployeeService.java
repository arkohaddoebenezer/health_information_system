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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Employee> getAllEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Doctor>> doctorsFuture = CompletableFuture.supplyAsync(doctorRepository::findAll);
        CompletableFuture<List<Nurse>> nursesFuture = CompletableFuture.supplyAsync(nurseRepository::findAll);
        List<Employee> employees = new ArrayList<>();
        employees.addAll(doctorsFuture.get());
        employees.addAll(nursesFuture.get());
        return employees;
    }

    @Cacheable(cacheNames = "employee", key = "#employeeNumber")
    public Optional<Employee> findEmployee(String employeeNumber) {
        logger.info("Fetching employee from database: employeeNumber=" + employeeNumber);

        // Initiate asynchronous calls to both repositories
        CompletableFuture<Optional<Doctor>> doctorFuture = CompletableFuture.supplyAsync(() -> doctorRepository.findByEmployeeNumber(employeeNumber));
        CompletableFuture<Optional<Nurse>> nurseFuture = CompletableFuture.supplyAsync(() -> nurseRepository.findByEmployeeNumber(employeeNumber));


        return (Optional<Employee>) doctorFuture.thenCombine(nurseFuture, (doctorOpt, nurseOpt) -> {
            if (doctorOpt.isPresent()) {
                return Optional.of((Employee) doctorOpt.get());
            }

            else if (nurseOpt.isPresent()) {
                logger.info("Nurse found: employeeNumber=" + employeeNumber);
                return Optional.of((Employee) nurseOpt.get());
            }
            else {
                logger.info("No employee found: employeeNumber=" + employeeNumber);
                return Optional.empty();
            }
        }).join();
    }


    public List<Employee> findEmployeesBySurname(String surname) {

        CompletableFuture<List<Employee>> doctorFuture = CompletableFuture.supplyAsync(() -> (List<Employee>) doctorRepository.findBySurname(surname));
        CompletableFuture<List<Employee>> nurseFuture = CompletableFuture.supplyAsync(() -> (List<Employee>) nurseRepository.findBySurname(surname));

        return doctorFuture.thenCombine(nurseFuture, (doctorList, nurseList) -> {
            return Stream.concat(
                    (doctorList != null ? doctorList.stream() : Stream.empty()),
                    (nurseList != null ? nurseList.stream() : Stream.empty())
            ).collect(Collectors.toList());
        }).join();
    }


    @Transactional
    @CacheEvict(value = "employee",key = "#employeeNumber")
    public void deleteEmployeeByEmployeeNumber(String employeeNumber) {

        if (doctorRepository.existsByEmployeeNumber(employeeNumber)) {
            Optional<Doctor> doctor = doctorRepository.findByEmployeeNumber(employeeNumber);
            List<Hospitalization> relatedHospitalizations = hospitalizationRepository.findByDoctor(doctor);
            for (Hospitalization hospitalization : relatedHospitalizations) {
                hospitalization.setDoctor(null);
                hospitalizationRepository.save(hospitalization);
            }
            Department departmentDirectedByDoctor = departmentRepository.findByDirector(doctor);
            if (departmentDirectedByDoctor != null) {
                departmentDirectedByDoctor.setDirector(null);
                departmentRepository.save(departmentDirectedByDoctor);
            }
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
    @CachePut(cacheNames="employee", key="#doctor.employeeNumber")
    public Doctor saveDoctor(Doctor doctor) {
        System.out.println("Save/Update Doctor");
        return doctorRepository.save(doctor);
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }
}
