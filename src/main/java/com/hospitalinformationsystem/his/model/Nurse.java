package com.hospitalinformationsystem.his.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Nurse extends Employee {

    @Id
    private Long nurseId;

    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
    private Department department;


    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurse_id) {
        this.nurseId = nurse_id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
