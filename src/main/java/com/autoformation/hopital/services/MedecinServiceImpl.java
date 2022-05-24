package com.autoformation.hopital.services;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.entities.StatusRDV;
import com.autoformation.hopital.repositories.MedecinRepository;
import com.autoformation.hopital.repositories.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedecinServiceImpl implements IMedecinService {

    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository) {
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
    }


    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin.toMedecinEntity()).toMedecin();
    }

    @Override
    public Medecin updateMedecin(Medecin medecin, Long id) {
        if (!getMedecinById(id).isPresent()){
            medecin.setId(id);
            return medecinRepository.save(medecin.toMedecinEntity()).toMedecin();
        }
        throw new RuntimeException("Medecin not found");
    }

    @Override
    public void deleteMedecinById(Long id) {
        medecinRepository.deleteById(id);

    }

    @Override
    public List<Medecin> getAllMedecin() {
        return medecinRepository.findAll().stream().
                map(medecin->medecin.toMedecin()).collect(Collectors.toList());
    }

    @Override
    public Page<Medecin> getAllMedecin(int page, int size) {
        Page<MedecinEntity> pageMedecinEntity = medecinRepository.findAll(PageRequest.of(page,size));
        List<Medecin> listMedecin =pageMedecinEntity.stream()
                .map(medecinEntity -> medecinEntity.toMedecin()).collect(Collectors.toList());
        return (Page<Medecin>)listMedecin;

    }

    @Override
    public Page<Medecin> getMedecinByName(String kw, Pageable page) {
        return (Page<Medecin>) medecinRepository.findByNomContains(kw, page).stream()
                .map(medecinEntity -> medecinEntity.toMedecin()).collect(Collectors.toList());
    }

    @Override
    public Optional<Medecin> getMedecinById(Long id) {
        Optional<Medecin> medecin;
        Optional<MedecinEntity> medecinEntity = medecinRepository.findById(id);
        if(medecinEntity.isPresent())  return Optional.ofNullable(medecinEntity.get().toMedecin());
        return Optional.ofNullable(null);
    }

    @Override
    public Collection<MedecinEntity> getMedecinByName(String name) {

        return medecinRepository.findByNom(name);
    }

    @Override
    public List<RendezVous> getRdvMedecin(Long id, StatusRDV status) {

        Optional<MedecinEntity> medecinEntity = medecinRepository.findById(id);
        if(medecinEntity.isPresent()){
            return medecinEntity.get().getRendezVous().stream()
                    .filter(rdv->rdv.getStatus()== status)
                    .map(rendezVousEntity -> rendezVousEntity.toRendezVous())
                    .collect(Collectors.toList());
        }
        return null;
    }
    @Override
    public Collection<RendezVous> getOpenRdvMedecinById(Long id) {

        Optional<MedecinEntity> medecinEntity = medecinRepository.findById(id);
        if(medecinEntity.isPresent()){
            return (medecinEntity.get().getRendezVous().stream()
                    .filter(r->r.getStatus()== StatusRDV.OPEN)
                    .map(rendezVousEntity -> rendezVousEntity.toRendezVous())
                    .collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public Collection<RendezVous> getPendingRdvMedecinById(Long id) {

        Optional<MedecinEntity> medecinEntity = medecinRepository.findById(id);
        if(medecinEntity.isPresent()){
            return (medecinEntity.get().getRendezVous().stream()
                    .filter(r->r.getStatus()== StatusRDV.PENDING)
                    .map(rendezVousEntity -> rendezVousEntity.toRendezVous())
                    .collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public RendezVous openRdvMedecin(long id, RendezVous rendezVous) {
        Optional<MedecinEntity> medecinEntity = medecinRepository.findById(id);
        if(medecinEntity.isPresent()){
            RendezVousEntity rendezVousEntity = rendezVous.toRendezVousEntity();
            /**  to verify (later) if RDV is already created (medecin, date, heure) */
            rendezVousEntity.setId(null); // generated by SGBD
            rendezVousEntity.setMedecin(medecinEntity.get());
            rendezVousEntity.setStatus(StatusRDV.OPEN);
            RendezVousEntity createdRdv = rendezVousRepository.save(rendezVousEntity);
            medecinEntity.get().getRendezVous().add(rendezVousEntity);
            return createdRdv.toRendezVous();
        }
        return null;
    }
}
