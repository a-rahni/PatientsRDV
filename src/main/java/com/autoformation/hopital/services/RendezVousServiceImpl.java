package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.entities.StatusRDV;
import com.autoformation.hopital.repositories.PatientRepository;
import com.autoformation.hopital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RendezVousServiceImpl implements IRendezVousService {

    private RendezVousRepository rendezVousRepository;
    private PatientRepository patientRepository;
    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository, PatientRepository patientRepository){
        this.rendezVousRepository =rendezVousRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public RendezVousEntity saveRendezVous(RendezVousEntity rendezVous) {
        // traitement Métier avant le save: check date, disponibilté medecin ..
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Optional<RendezVousEntity> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id);
    }

    @Override
    public RendezVous addPatientToRdv(Long id, Patient patient) {

        Optional<RendezVousEntity> rendezVousEntity = rendezVousRepository.findById(id);
        Optional<PatientEntity> patientEntity = patientRepository.findById(patient.getId());
        if(rendezVousEntity.isPresent() && patientEntity.isPresent()){
            rendezVousEntity.get().setPatient(patientEntity.get());
            rendezVousEntity.get().setStatus(StatusRDV.PENDING);
            patientEntity.get().getRendezVous().add(rendezVousEntity.get());
            return rendezVousRepository.save(rendezVousEntity.get()).toRendezVous();
        }
        return null;
    }

    @Override
    public void deleteRDVById(Long id) {
        rendezVousRepository.deleteById(id);
    }
}
