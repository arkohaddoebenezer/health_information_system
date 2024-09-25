package com.hospitalinformationsystem.his.controller;

import com.hospitalinformationsystem.his.model.ReleaseNote;
import com.hospitalinformationsystem.his.repository.ReleaseNotesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Endpoint(id="releaseNotes")
public class ReleaseNotesEndpoint {

    @Autowired
    private ReleaseNotesDataRepository releaseNotesDataRepository;


    @ReadOperation
    public ResponseEntity<List<ReleaseNote>> getReleaseNotes() {
        return ResponseEntity.ok(releaseNotesDataRepository.findAll());
    }

    @WriteOperation
    public ResponseEntity<ReleaseNote> addReleaseNote(@RequestBody String version,@RequestBody String changeLogData) {
     ReleaseNote releaseNote = new ReleaseNote(version,LocalDateTime.now(),changeLogData);
        return ResponseEntity.ok(releaseNotesDataRepository.save(releaseNote));
    }

    @DeleteOperation
    public void deleteReleaseNote(@Selector String version) {
        releaseNotesDataRepository.deleteById(version);
    }
}
