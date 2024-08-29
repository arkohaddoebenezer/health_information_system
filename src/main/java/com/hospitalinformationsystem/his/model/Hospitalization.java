package com.hospitalinformationsystem.his.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Hospitalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_number", referencedColumnName = "patientNumber")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "wardID")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    private Doctor doctor;

    private Long bedNumber;
    private String diagnosis;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Long bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDateTime getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDateTime admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDateTime dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
