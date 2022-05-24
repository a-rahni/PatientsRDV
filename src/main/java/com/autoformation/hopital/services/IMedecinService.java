package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.entities.StatusRDV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IMedecinService {
    Medecin saveMedecin(Medecin patient);

    Medecin updateMedecin(Medecin medecin, Long id);
    void deleteMedecinById(Long id);

    List<Medecin> getAllMedecin();
    Page<Medecin> getAllMedecin(int page, int size);

    Page<Medecin> getMedecinByName(String kw, Pageable page);
    Optional<Medecin> getMedecinById(Long id);
    Collection<MedecinEntity> getMedecinByName(String name);
    //Iterable<Medecin> getMedecinByName(String name);

    List<RendezVous> getRdvMedecin(Long id, StatusRDV status);

    Collection<RendezVous> getOpenRdvMedecinById(Long id);

    Collection<RendezVous> getPendingRdvMedecinById(Long id);

    RendezVous openRdvMedecin( long id, RendezVous rendezVous);
}
