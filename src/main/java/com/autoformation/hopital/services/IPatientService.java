package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.StatusRDV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient savePatient(Patient patient);
    Patient updatePatient(Patient patient, Long id);
    void deletePatientById(Long id);

    /* getters*/
    List<Patient> getAllPatient();
    Page<Patient> getAllPatient(int page, int size);

    Page<Patient> getPatientByName(String kw, Pageable page);
    Optional<Patient> getPatientById(Long id);
    List<Patient> getPatientByName(String name);
    //Iterable<Patient> getPatientByName(String name);

    List<RendezVous> getRdvPatient(Long id, StatusRDV status);

}
