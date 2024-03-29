package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.StatusRDV;
import com.autoformation.hopital.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        if(patient != null) {
            PatientEntity patientEntity = patient.toPatientEntity();
            //if (!patientRepository.findById(patientEntity.getId()).isPresent())
            return patientRepository.save(patientEntity).toPatient();
            //throw new RuntimeException("Add new Patient with existing id is not possible");
        }
        throw new RuntimeException("a new Patient must be not null");
    }

    @Override
    public Patient updatePatient(Patient patient, Long id) {
        if (patientRepository.findById(id).isPresent()){
            patient.setId(id);
            return patientRepository.save(patient.toPatientEntity()).toPatient();
        }
        throw new RuntimeException("Patient not found");
    }

    @Override
    public void deletePatientById(Long id){
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll().stream().map(p->p.toPatient()).collect(Collectors.toList());
    }

    @Override
    public Page<Patient> getAllPatient(int page, int size) {
        Page<PatientEntity> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        return new PageImpl<Patient>(pagePatients.stream()
                .map(patientEntity -> patientEntity.toPatient()).collect(Collectors.toList()),
                pagePatients.getPageable(),
                pagePatients.getTotalElements()
        );
    }

    @Override
    public Page<Patient> getPatientByName(String kw, Pageable page) {
        if(kw != null) {
            Page<PatientEntity> pagePatients = patientRepository.findByNomContains(kw, page);
            return new PageImpl<Patient>(pagePatients.stream()
                    .map(patientEntity -> patientEntity.toPatient()).collect(Collectors.toList()),
                    pagePatients.getPageable(),
                    pagePatients.getTotalElements()
            );
        }
        throw new RuntimeException("the searched name must be not null");
    }

    @Override
    public List<Patient> getPatientByName(String name) {

        if(null != name) {
            return patientRepository.findByNom(name).stream()
                    .map(patientEntity -> patientEntity.toPatient())
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("the searched name must be not null");
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        Optional<PatientEntity> patientEntity = patientRepository.findById(id);
        if(patientEntity.isPresent())  return Optional.ofNullable(patientEntity.get().toPatient());
        return Optional.ofNullable(null);
    }

    @Override
    public List<RendezVous> getRdvPatient(Long id, StatusRDV status) {

        Optional<PatientEntity> patientEntity = patientRepository.findById(id);
        if(patientEntity.isPresent()){
            return patientEntity.get().getRendezVous().stream()
                    .filter(rdv->rdv.getStatus()== status)
                    .map(rendezVousEntity -> rendezVousEntity.toRendezVous())
                    .collect(Collectors.toList());
        }
        return null;

    }

}
