package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Medecin;
import com.autoformation.hopital.entities.RendezVous;
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
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecinById(Long id) {
        medecinRepository.deleteById(id);

    }

    @Override
    public Collection<Medecin> getAllMedecin() {

        return medecinRepository.findAll();
    }

    @Override
    public Page<Medecin> getAllMedecin(int page, int size) {
        return medecinRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Page<Medecin> getMedecinByName(String kw, Pageable page) {
        return medecinRepository.findByNomContains(kw, page);
    }

    @Override
    public Optional<Medecin> getMedecinById(Long id) {

        return medecinRepository.findById(id);
    }

    @Override
    public Collection<Medecin> getMedecinByName(String name) {

        return medecinRepository.findByNom(name);
    }

    @Override
    public Collection<RendezVous> getOpenRdvMedecinById(Long id) {

        Optional<Medecin> m = medecinRepository.findById(id);
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
}
