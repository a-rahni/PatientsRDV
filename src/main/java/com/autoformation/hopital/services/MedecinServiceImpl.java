package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Medecin;
import com.autoformation.hopital.repositories.MedecinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MedecinServiceImpl implements IMedecinService {

    private MedecinRepository medecinRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        // traitement
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecinById(Long id) {
        medecinRepository.deleteById(id);
    }

    @Override
    public Iterable<Medecin> getAllMedecin() {

        return medecinRepository.findAll();
    }

    @Override
    public Optional<Medecin> getMedecintById(Long id) {

        return medecinRepository.findById(id);
    }

    @Override
    public Iterable<Medecin> getMedecinByName(String name) {

        return medecinRepository.findByNom(name);
    }
}
