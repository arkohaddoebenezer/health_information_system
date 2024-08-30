package com.hospitalinformationsystem.his.controller;



import com.hospitalinformationsystem.his.model.Doctor;
import com.hospitalinformationsystem.his.model.Employee;
import com.hospitalinformationsystem.his.model.Nurse;
import com.hospitalinformationsystem.his.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = new Doctor();
        Employee employee2 = new Nurse();

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testFindEmployeeByEmployeeNumber() throws Exception {
        String employeeNumber = "123";
        Employee employee = new Doctor();
        employee.setEmployeeNumber(employeeNumber);

        when(employeeService.findEmployeeByEmployeeNumber(employeeNumber)).thenReturn(Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByEmployeeNumber/{employeeNumber}", employeeNumber))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeNumber").value(employeeNumber));
    }

    @Test
    void testFindEmployeeByEmployeeNumber_NotFound() throws Exception {
        String employeeNumber = "123";

        when(employeeService.findEmployeeByEmployeeNumber(employeeNumber)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByEmployeeNumber/{employeeNumber}", employeeNumber))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testFindEmployeesByLastName() throws Exception {
        String lastName = "Doe";
        Employee employee1 = new Doctor();
        Employee employee2 = new Nurse();
        employee1.setSurname(lastName);
        employee2.setSurname(lastName);

        when(employeeService.findEmployeesByLastName(lastName)).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByLastName/{lastName}", lastName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testFindDoctorsBySpecialization() throws Exception {
        String specialization = "Cardiology";
        Doctor doctor = new Doctor();
        doctor.setSpeciality(specialization);

        when(employeeService.findDoctorsBySpecialization(specialization)).thenReturn(Arrays.asList(doctor));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/doctors/findBySpecialization/{specialization}", specialization))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setSurname("Doe");

        when(employeeService.saveDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(doctor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Doe"));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setSurname("Doe");

        when(employeeService.saveDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(doctor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Doe"));
    }

    @Test
    void testDeleteEmployeeByEmployeeNumber() throws Exception {
        String employeeNumber = "123";

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/delete/{employeeNumber}", employeeNumber))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
