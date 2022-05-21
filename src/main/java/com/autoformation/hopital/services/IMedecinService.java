package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IMedecinService {
    MedecinEntity saveMedecin(MedecinEntity patient);
    void deleteMedecinById(Long id);

    Collection<MedecinEntity> getAllMedecin();
    Page<MedecinEntity> getAllMedecin(int page, int size);

    Page<MedecinEntity> getMedecinByName(String kw, Pageable page);
    Optional<MedecinEntity> getMedecinById(Long id);
    Collection<MedecinEntity> getMedecinByName(String name);
    //Iterable<Medecin> getMedecinByName(String name);

    Collection<RendezVousEntity> getOpenRdvMedecinById(Long id);

    Collection<RendezVousEntity> getPendingRdvMedecinById(Long id);
}
