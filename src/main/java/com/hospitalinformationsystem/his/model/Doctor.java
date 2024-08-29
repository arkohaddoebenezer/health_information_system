package com.hospitalinformationsystem.his.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor extends Employee {

    @Id
    private Long doctorId;
    private String speciality;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctor_id) {
        this.doctorId = doctor_id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
