 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.Entity;
 import jakarta.persistence.Id;

 @Entity
 public class Doctor extends Employee {


     private String speciality;


     public String getSpeciality() {
         return speciality;
     }

     public void setSpeciality(String speciality) {
         this.speciality = speciality;
     }
 }
