package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Patient;
import com.autoformation.hopital.entities.RendezVous;
import com.autoformation.hopital.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);

    }

    @Override
    public Collection<Patient> getAllPatient() {

        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAllPatient(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Page<Patient> getPatientByName(String kw, Pageable page) {
        return patientRepository.findByNomContains(kw, page);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {

        return patientRepository.findById(id);
    }

    @Override
    public Collection<Patient> getPatientByName(String name) {

        return patientRepository.findByNom(name);
    }

}
