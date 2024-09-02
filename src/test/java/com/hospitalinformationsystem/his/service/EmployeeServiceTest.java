package com.hospitalinformationsystem.his.service;

import static org.junit.jupiter.api.Assertions.*;
import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Employee;
import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.repository.DoctorRepository;
import com.hospitalinformationsystem.his.repository.NurseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private NurseRepository nurseRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();

        when(doctorRepository.findAll()).thenReturn((List) Arrays.asList(doctor));
        when(nurseRepository.findAll()).thenReturn((List) Arrays.asList(nurse));

        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
        verify(doctorRepository, times(1)).findAll();
        verify(nurseRepository, times(1)).findAll();
    }

    @Test
    void testFindEmployeeByEmployeeNumber_DoctorFound() {
        String employeeNumber = "123";
        Doctor doctor = new Doctor();
        doctor.setEmployeeNumber(employeeNumber);

        when(doctorRepository.findByEmployeeNumber(employeeNumber)).thenReturn(Optional.of(doctor));

        Optional<Employee> result = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
        assertTrue(result.isPresent());
        assertEquals(employeeNumber, result.get().getEmployeeNumber());
        verify(doctorRepository, times(1)).findByEmployeeNumber(employeeNumber);
        verify(nurseRepository, never()).findByEmployeeNumber(employeeNumber);
    }

    @Test
    void testFindEmployeeByEmployeeNumber_NurseFound() {
        String employeeNumber = "123";
        Nurse nurse = new Nurse();
        nurse.setEmployeeNumber(employeeNumber);

        when(nurseRepository.findByEmployeeNumber(employeeNumber)).thenReturn(Optional.of(nurse));

        Optional<Employee> result = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
        assertTrue(result.isPresent());
        assertEquals(employeeNumber, result.get().getEmployeeNumber());
        verify(doctorRepository, times(1)).findByEmployeeNumber(employeeNumber);
        verify(nurseRepository, times(1)).findByEmployeeNumber(employeeNumber);
    }

    @Test
    void testFindEmployeeByEmployeeNumber_NotFound() {
        String employeeNumber = "123";

        when(doctorRepository.findByEmployeeNumber(employeeNumber)).thenReturn(Optional.empty());
        when(nurseRepository.findByEmployeeNumber(employeeNumber)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
        assertFalse(result.isPresent());
        verify(doctorRepository, times(1)).findByEmployeeNumber(employeeNumber);
        verify(nurseRepository, times(1)).findByEmployeeNumber(employeeNumber);
    }

    @Test
    void testFindEmployeesByLastName() {
        String lastName = "Doe";
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();

        when(doctorRepository.findBySurname(lastName)).thenReturn((List) Arrays.asList(doctor));
        when(nurseRepository.findBySurname(lastName)).thenReturn((List) Arrays.asList(nurse));

        List<Employee> result = employeeService.findEmployeesBySurname(lastName);
        assertEquals(2, result.size());
        verify(doctorRepository, times(1)).findBySurname(lastName);
        verify(nurseRepository, times(1)).findBySurname(lastName);
    }

    @Test
    void testDeleteEmployeeByEmployeeNumber_DoctorExists() {
        String employeeNumber = "123";

        when(doctorRepository.existsByEmployeeNumber(employeeNumber)).thenReturn(true);

        employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);

        verify(doctorRepository, times(1)).deleteByEmployeeNumber(employeeNumber);
        verify(nurseRepository, never()).deleteByEmployeeNumber(anyString());
    }

    @Test
    void testDeleteEmployeeByEmployeeNumber_NurseExists() {
        String employeeNumber = "123";

        when(nurseRepository.existsByEmployeeNumber(employeeNumber)).thenReturn(true);

        employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);

        verify(nurseRepository, times(1)).deleteByEmployeeNumber(employeeNumber);
        verify(doctorRepository, never()).deleteByEmployeeNumber(anyString());
    }

    @Test
    void testFindDoctorsBySpecialization() {
        String specialization = "Cardiology";
        Doctor doctor = new Doctor();
        doctor.setSpeciality(specialization);

        when(doctorRepository.findDoctorBySpeciality(specialization)).thenReturn((List) Arrays.asList(doctor));

        List<Doctor> result = employeeService.findDoctorsBySpecialization(specialization);
        assertEquals(1, result.size());
        assertEquals(specialization, result.get(0).getSpeciality());
        verify(doctorRepository, times(1)).findDoctorBySpeciality(specialization);
    }

    @Test
    void testSaveDoctor() {
        Doctor doctor = new Doctor();
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor result = employeeService.saveDoctor(doctor);
        assertEquals(doctor, result);
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    void testSaveNurse() {
        Nurse nurse = new Nurse();
        when(nurseRepository.save(nurse)).thenReturn(nurse);

        Nurse result = employeeService.saveNurse(nurse);
        assertEquals(nurse, result);
        verify(nurseRepository, times(1)).save(nurse);
    }
}
