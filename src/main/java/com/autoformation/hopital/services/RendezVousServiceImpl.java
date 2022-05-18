package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.RendezVous;
import com.autoformation.hopital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RendezVousServiceImpl implements IRendezVousService {

    RendezVousRepository rendezVousRepository;
    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository){
        this.rendezVousRepository =rendezVousRepository;
    }
    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        // traitement Métier avant le save: check date, disponibilté medecin ..
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Optional<RendezVous> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id);
    }

    @Override
    public void deleteRDVById(Long id) {
        rendezVousRepository.deleteById(id);
    }
}
