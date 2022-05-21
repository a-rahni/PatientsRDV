package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.ConsultationEntity;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.RendezVousEntity;

public interface IHospitalService {
    PatientEntity savePatient(PatientEntity patient);
    MedecinEntity saveMedecin(MedecinEntity medecin);
    RendezVousEntity saveRDV(RendezVousEntity rendezVous);
    ConsultationEntity saveConsultation(ConsultationEntity consultation);
}
