package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Medecin;

import java.util.Optional;

public interface IMedecinService {
    Medecin saveMedecin(Medecin patient);
    void deleteMedecinById(Long id);

    /* getters*/
    Iterable<Medecin> getAllMedecin();
    Optional<Medecin> getMedecintById(Long id);
    Iterable<Medecin> getMedecinByName(String name);
}
