package com.hospitalinformationsystem.his.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee {
    @Embedded
    private Contact contact;
}
