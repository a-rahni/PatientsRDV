package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IPatientService {
    PatientEntity savePatient(PatientEntity patient);
    void deletePatientById(Long id);

    /* getters*/
    Collection<PatientEntity> getAllPatient();
    Page<PatientEntity> getAllPatient(int page, int size);

    Page<PatientEntity> getPatientByName(String kw, Pageable page);
    Optional<PatientEntity> getPatientById(Long id);
    Collection<PatientEntity> getPatientByName(String name);
    //Iterable<Patient> getPatientByName(String name);

}
