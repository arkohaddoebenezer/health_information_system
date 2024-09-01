 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.*;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;

 import java.util.List;

 @Entity
 public class Department {

     @Id
     @NotNull(message = "Department code cannot be null")
     @NotBlank(message = "Department code cannot be empty")
     private Long departmentCode;

     @NotNull(message = "Department name cannot be null")
     @NotBlank(message = "Department name cannot be empty")
     private String name;

     @NotNull(message = "Department building cannot be null")
     @NotBlank(message = "Department building cannot be empty")
     private String building;

     @OneToOne
     @JoinColumn(name = "director_id", referencedColumnName = "employeeNumber")
     private Doctor director;

     @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
     private List<Ward> wards;


     public Long getDepartmentCode() {
         return departmentCode;
     }

     public void setDepartmentCode(Long departmentCode) {
         this.departmentCode = departmentCode;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getBuilding() {
         return building;
     }

     public void setBuilding(String building) {
         this.building = building;
     }

     public Doctor getDirector() {
         return director;
     }

     public void setDirector(Doctor director) {
         this.director = director;
     }

     public List<Ward> getWards() {
         return wards;
     }

     public void setWards(List<Ward> wards) {
         this.wards = wards;
     }
 }
