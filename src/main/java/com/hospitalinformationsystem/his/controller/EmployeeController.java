package com.hospitalinformationsystem.his.controller;
import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Employee;
import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Employee> findEmployee(@PathVariable String employeeNumber) {
        Optional<Employee> employee = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/findByEmployeeNumber/{employeeNumber}")
    public ResponseEntity<Employee> findEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        Optional<Employee> employee = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findBySurname/{lastName}")
    public List<Employee> findEmployeesBySurname(@PathVariable String lastName) {
        return employeeService.findEmployeesBySurname(lastName);
    }


    @GetMapping("/doctors/findBySpecialization/{specialization}")
    public List<Doctor> findDoctorsBySpecialization(@PathVariable String specialization) {
        return employeeService.findDoctorsBySpecialization(specialization);
    }


    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        System.out.println(doctor.getSurname());
        Doctor savedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }


    @PutMapping("/nurses")
    public ResponseEntity<Nurse> updateNurse(@RequestBody Nurse nurse) {
        Nurse updatedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(updatedNurse);
    }
    @PutMapping("/doctors")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        Doctor updatedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PostMapping("/nurses")
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
        Nurse savedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(savedNurse);
    }

    @DeleteMapping("/delete/{employeeNumber}")
    public ResponseEntity<Void> deleteEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);
        return ResponseEntity.noContent().build();
    }


}
