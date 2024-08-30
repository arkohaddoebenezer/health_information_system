 package com.hospitalinformationsystem.his.model;

 import jakarta.annotation.Nullable;
 import jakarta.persistence.*;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;

 @MappedSuperclass
 public abstract class Employee {
     @Id
     @NotNull
     @NotBlank
     @Column(unique = true)
     String employeeNumber;

     @NotNull(message = "first name should be provided")
     @NotBlank(message = "first name should not be empty")
     private String firstname;

     @NotNull(message = "surname name should be provided")
     @NotBlank(message = "surname name should not be empty")
     private String surname;

     @Nullable
     private String telephoneNumber;

     @Nullable
     private String address;

     public String getEmployeeNumber() {
         return employeeNumber;
     }

     public void setEmployeeNumber(String EmployeeNumber) {
         this.employeeNumber = EmployeeNumber;
     }


     public String getFirstname() {
         return firstname;
     }

     public void setFirstname(String firstname) {
         this.firstname = firstname;
     }

     public String getSurname() {
         return surname;
     }

     public void setSurname(String surname) {
         this.surname = surname;
     }

     public String getTelephoneNumber() {
         return telephoneNumber;
     }

     public void setTelephoneNumber(String telephoneNumber) {
         this.telephoneNumber = telephoneNumber;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }
 }
