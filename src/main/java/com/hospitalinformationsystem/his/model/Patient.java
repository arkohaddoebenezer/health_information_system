package com.hospitalinformationsystem.his.model;

import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientNumber;

    @Embedded
    private Contact contact;

    public Long getNumber() {
        return patientNumber;
    }

    public void setNumber(Long number) {
        this.patientNumber = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
