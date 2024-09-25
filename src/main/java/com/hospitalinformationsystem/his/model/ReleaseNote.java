package com.hospitalinformationsystem.his.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ReleaseNote {

    @Id
    private String version;
    private LocalDateTime releaseDate;
    private String changeLogData;

    public ReleaseNote(String version, LocalDateTime releaseDate, String changeLogData) {
        this.version = version;
        this.releaseDate = releaseDate;
        this.changeLogData = changeLogData;
    }

    public ReleaseNote() {
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getChangeLogData() {
        return changeLogData;
    }

    public void setChangeLogData(String changeLogData) {
        this.changeLogData = changeLogData;
    }
}
