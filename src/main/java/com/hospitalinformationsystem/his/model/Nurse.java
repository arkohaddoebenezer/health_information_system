 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.Entity;
 import jakarta.persistence.JoinColumn;
 import jakarta.persistence.ManyToOne;

 @Entity
 public class Nurse extends Employee {

     @ManyToOne
     @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
     private Department assignedDepartment;

     @ManyToOne
     @JoinColumn(name = "assigned_ward", referencedColumnName = "wardID")
     private Ward assignedWard;

     private Long salary;
     private String rotation;

     public Department getAssignedDepartment() {
         return assignedDepartment;
     }

     public void assignToWard(Ward ward) {
         this.assignedWard = ward;
     }
 }
