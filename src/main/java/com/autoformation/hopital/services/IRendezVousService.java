package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.RendezVousEntity;

import java.util.Optional;

public interface IRendezVousService {

    RendezVousEntity saveRendezVous(RendezVousEntity rendezVous);
    Optional<RendezVousEntity> getRendezVousById(Long id);


    void deleteRDVById(Long id);


}
