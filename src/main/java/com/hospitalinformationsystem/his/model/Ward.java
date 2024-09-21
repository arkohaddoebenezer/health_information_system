 package com.hospitalinformationsystem.his.model;
 import jakarta.persistence.*;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;


 @Entity
 public class Ward{
     @Id
     private String wardID;
     @NotNull(message = "Ward name is required")
     @NotBlank(message = "Ward name cannot be empty")
     private String name;

     @NotNull(message = "Ward number is required")
     @NotBlank(message = "Ward number cannot be empty")
     private Long wardNumber;

     @NotNull(message = "Number of beds required")
     @NotBlank(message = "Number of beds cannot be empty")
     private Long numberOfBeds;

     private Long availableBeds;

     @ManyToOne
     @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
     private Department department;

     @OneToOne
     @JoinColumn(name = "supervisor_id", referencedColumnName = "employeeNumber")
     private Nurse supervisor;


     public String getWardID() {
         return wardID;
     }

     public void setWardID(Long departmentId, Long wardNumber) {
         this.wardID = ""+departmentId+wardNumber;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public Long getWardNumber() {
         return wardNumber;
     }

     public void setWardNumber(Long wardNumber) {
         this.wardNumber = wardNumber;
     }

     public Long getNumberOfBeds() {
         return numberOfBeds;
     }

     public void setNumberOfBeds(Long numberOfBeds) {
         this.numberOfBeds = numberOfBeds;
     }

     public Department getDepartment() {
         return department;
     }

     public void setDepartment(Department department) {
         this.department = department;
     }

     public Nurse getSupervisor() {
         return supervisor;
     }

     public void setSupervisor(Nurse supervisor) {
         this.supervisor = supervisor;
     }

     public Long getAvailableBeds() {
         return availableBeds;
     }

     public void setAvailableBeds(Long availableBeds) {
         this.availableBeds = availableBeds;
     }
 }
