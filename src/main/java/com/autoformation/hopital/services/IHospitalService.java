package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Consultation;
import com.autoformation.hopital.entities.Medecin;
import com.autoformation.hopital.entities.Patient;
import com.autoformation.hopital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
