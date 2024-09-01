package com.hospitalinformationsystem.his.service;

import com.hospitalinformationsystem.his.model.Department;
import com.hospitalinformationsystem.his.model.Hospitalization;
import com.hospitalinformationsystem.his.model.Patient;
import com.hospitalinformationsystem.his.model.Ward;
import com.hospitalinformationsystem.his.repository.HospitalizationRepository;
import com.hospitalinformationsystem.his.repository.WardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalizationService {

    private final WardRepository wardRepository;
    private final HospitalizationRepository hospitalizationRepository;

    public HospitalizationService(WardRepository wardRepository, HospitalizationRepository hospitalizationRepository) {
        this.wardRepository = wardRepository;
        this.hospitalizationRepository = hospitalizationRepository;
    }

    @Transactional
    public void admitPatient(Patient patient, Department department) {
        List<Ward> wardsInDepartment = department.getWards();

        Optional<Ward> optionalWard = wardsInDepartment.stream()
                .filter(ward -> ward.getAvailableBeds() > 0)
                .findFirst();

        if (optionalWard.isPresent()) {
            Ward ward = optionalWard.get();

            ward.setAvailableBeds(ward.getAvailableBeds() - 1);
            wardRepository.save(ward);

            Hospitalization hospitalization = new Hospitalization();
            hospitalization.setPatient(patient);
            hospitalization.setWard(ward);
            hospitalization.setAdmissionDate(new Date());
            hospitalizationRepository.save(hospitalization);
        } else {
            throw new RuntimeException("No available beds in the department");
        }
    }
}
