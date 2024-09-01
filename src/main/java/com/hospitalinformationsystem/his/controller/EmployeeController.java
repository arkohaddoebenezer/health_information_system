package com.hospitalinformationsystem.his.controller;
import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Employee;
import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{employeeNumber}")
    @Cacheable(cacheNames="employee", key="#employeeNumber")
    public ResponseEntity<Employee> findEmployee(@PathVariable String employeeNumber) {
        Optional<Employee> employee = employeeService.findEmployee(employeeNumber);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findBySurname/{surname}")
    public List<Employee> findEmployeesBySurname(@PathVariable String surname) {
        return employeeService.findEmployeesBySurname(surname);
    }


    @GetMapping("/doctors/findBySpecialization/{specialization}")
    public List<Doctor> findDoctorsBySpecialization(@PathVariable String specialization) {
        return employeeService.findDoctorsBySpecialization(specialization);
    }


    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @PutMapping("/nurses")
    public ResponseEntity<Nurse> updateNurse(@Valid @RequestBody Nurse nurse) {
        Nurse updatedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(updatedNurse);
    }
    @PutMapping("/doctors")
    @CachePut(cacheNames="employee", key="#doctor.employeeNumber")
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor updatedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PostMapping("/nurses")
    public ResponseEntity<Nurse> createNurse(@Valid @RequestBody Nurse nurse) {
        Nurse savedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(savedNurse);
    }

    @DeleteMapping("/delete/{employeeNumber}")
    @CacheEvict(value = "employee",key = "employeeNumber")
    public ResponseEntity<Void> deleteEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);
        return ResponseEntity.noContent().build();
    }


}
