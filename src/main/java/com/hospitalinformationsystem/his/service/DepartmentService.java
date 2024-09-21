 package com.hospitalinformationsystem.his.service;

 import com.hospitalinformationsystem.his.model.Department;
 import com.hospitalinformationsystem.his.repository.DepartmentRepository;
 import org.springframework.stereotype.Service;

 import java.util.List;

 @Service
 public class DepartmentService {
     private final DepartmentRepository departmentRepository;

     public DepartmentService(DepartmentRepository departmentRepository) {

         this.departmentRepository = departmentRepository;
     }


     public Department saveDoctor(Department department) {
         return departmentRepository.save(department);
     }


     public List<Department> getAllDoctors() {
         return departmentRepository.findAll();
     }


     public Department updateDepartment(Long departmentId, Department departmentDetails) {
         Department department =  departmentRepository.findById(departmentId).orElse(null);
         if (department != null) {
             return departmentRepository.save(departmentDetails);
         }
         return null;
     }

     public void deleteDepartment(Long id) {
         departmentRepository.deleteById(id);
     }
 }
