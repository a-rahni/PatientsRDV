package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.ConsultationEntity;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.repositories.ConsultationRepository;
import com.autoformation.hopital.repositories.MedecinRepository;
import com.autoformation.hopital.repositories.PatientRepository;
import com.autoformation.hopital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public PatientEntity savePatient(PatientEntity patient) {
        // traitement avant save: check ...
        return patientRepository.save(patient);
    }

    @Override
    public MedecinEntity saveMedecin(MedecinEntity medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVousEntity saveRDV(RendezVousEntity rendezVous) {

        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public ConsultationEntity saveConsultation(ConsultationEntity consultation) {

        return consultationRepository.save(consultation);
    }
}
