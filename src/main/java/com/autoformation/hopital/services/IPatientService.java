package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IPatientService {
    Patient savePatient(Patient patient);
    void deletePatientById(Long id);

    /* getters*/
    Collection<Patient> getAllPatient();
    Page<Patient> getAllPatient(int page, int size);

    Page<Patient> getPatientByName(String kw, Pageable page);
    Optional<Patient> getPatientById(Long id);
    Collection<Patient> getPatientByName(String name);
    //Iterable<Patient> getPatientByName(String name);
}
