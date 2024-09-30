 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.Entity;

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
