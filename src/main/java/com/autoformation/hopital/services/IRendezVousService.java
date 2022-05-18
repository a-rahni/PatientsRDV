package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.RendezVous;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRendezVousService {

    RendezVous saveRendezVous(RendezVous rendezVous);
    Optional<RendezVous> getRendezVousById(Long id);


    void deleteRDVById(Long id);


}
