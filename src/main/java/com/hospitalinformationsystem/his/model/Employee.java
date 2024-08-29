 package com.hospitalinformationsystem.his.model;

 import jakarta.persistence.Embedded;
 import jakarta.persistence.Id;
 import jakarta.persistence.MappedSuperclass;

 @MappedSuperclass
 public abstract class Employee {
     @Id
     String EmployeeNumber;

     @Embedded
     private Contact contact;


     public String getEmployeeNumber() {
         return EmployeeNumber;
     }

     public void setEmployeeNumber(String EmployeeNumber) {
         this.EmployeeNumber = EmployeeNumber;
     }
 }
