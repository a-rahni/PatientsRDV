package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.RendezVousEntity;

import java.util.Optional;

public interface IRendezVousService {

    RendezVousEntity saveRendezVous(RendezVousEntity rendezVous);
    Optional<RendezVousEntity> getRendezVousById(Long id);

    RendezVous addPatientToRdv(Long id, Patient patient);


    void deleteRDVById(Long id);


}
