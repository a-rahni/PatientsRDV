package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Medecin;
import com.autoformation.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IMedecinService {
    Medecin saveMedecin(Medecin patient);
    void deleteMedecinById(Long id);

    Collection<Medecin> getAllMedecin();
    Page<Medecin> getAllMedecin(int page, int size);

    Page<Medecin> getMedecinByName(String kw, Pageable page);
    Optional<Medecin> getMedecinById(Long id);
    Collection<Medecin> getMedecinByName(String name);
    //Iterable<Medecin> getMedecinByName(String name);
}
