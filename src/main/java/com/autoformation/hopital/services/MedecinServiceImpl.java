package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.entities.StatusRDV;
import com.autoformation.hopital.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedecinServiceImpl implements IMedecinService {

    private MedecinRepository medecinRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }


    @Override
    public MedecinEntity saveMedecin(MedecinEntity medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecinById(Long id) {
        medecinRepository.deleteById(id);

    }

    @Override
    public Collection<MedecinEntity> getAllMedecin() {

        return medecinRepository.findAll();
    }

    @Override
    public Page<MedecinEntity> getAllMedecin(int page, int size) {
        return medecinRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Page<MedecinEntity> getMedecinByName(String kw, Pageable page) {
        return medecinRepository.findByNomContains(kw, page);
    }

    @Override
    public Optional<MedecinEntity> getMedecinById(Long id) {

        return medecinRepository.findById(id);
    }

    @Override
    public Collection<MedecinEntity> getMedecinByName(String name) {

        return medecinRepository.findByNom(name);
    }

    @Override
    public Collection<RendezVousEntity> getOpenRdvMedecinById(Long id) {

        Optional<MedecinEntity> m = medecinRepository.findById(id);
        if(m.isPresent()){
            return (m.get().getRendezVous().stream()
                    .filter(r->r.getStatus()== StatusRDV.OPEN)
                    .collect(Collectors.toList()));
        }
        else
        {
            return null;
        }

    }

    @Override
    public Collection<RendezVousEntity> getPendingRdvMedecinById(Long id) {

        Optional<MedecinEntity> m = medecinRepository.findById(id);
        if(m.isPresent()){
            return (m.get().getRendezVous().stream()
                    .filter(r->r.getStatus()== StatusRDV.PENDING)
                    .collect(Collectors.toList()));
        }
        else
        {
            return null;
        }
    }
}
