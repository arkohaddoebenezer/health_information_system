 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.*;
 import java.util.List;

 @Entity
 public class Department {

     @Id
     private Long departmentCode;
     private String name;
     private String building;

     @OneToOne
     @JoinColumn(name = "director_id", referencedColumnName = "EmployeeNumber")
     private Doctor director;

     @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
     private List<Ward> wards;

     // Getters and Setters
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
