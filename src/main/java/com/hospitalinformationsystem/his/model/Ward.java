package com.hospitalinformationsystem.his.model;

import com.mysql.cj.log.Log;
import jakarta.persistence.*;

@Entity
public class Ward {

    @Id
    private String wardID;
    private String name;
    private Long wardNumber;
    private Long numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
    private Department department;

    @OneToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "nurseId")
    private Nurse supervisor;


    public String getWardID() {
        return wardID;
    }

    public void setWardID(Long departmentId, Long wardNumber) {
        this.wardID = ""+departmentId+wardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(Long wardNumber) {
        this.wardNumber = wardNumber;
    }

    public Long getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Long numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Nurse getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Nurse supervisor) {
        this.supervisor = supervisor;
    }
}
