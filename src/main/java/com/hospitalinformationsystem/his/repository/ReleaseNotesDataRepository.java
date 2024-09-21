package com.hospitalinformationsystem.his.repository;

import com.hospitalinformationsystem.his.model.ReleaseNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleaseNotesDataRepository extends JpaRepository<ReleaseNote, String> {
    List<ReleaseNote> findByVersionStartingWith(String prefix);

}
