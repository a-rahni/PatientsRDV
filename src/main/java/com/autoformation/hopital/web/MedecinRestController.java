package com.autoformation.hopital.web;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.services.IMedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medecins")
public class MedecinRestController {

    @Autowired
    private IMedecinService medecinService;

    @GetMapping("/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin getMedecin(@PathVariable Long id){

        Optional<MedecinEntity> m = medecinService.getMedecinById(id);
        if(m.isPresent()){
            return m.get().toMedecinDto();
        }
        else {
            return null;
        }
    }

    @GetMapping(path = "/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Medecin> medecinList(){
        return medecinService.getAllMedecin().stream().
                map(m->m.toMedecinDto()).collect(Collectors.toList());
    }

    // methode recherche avec pagination exp  http://localhost:8086/medecins?(page=1,size=5,keyword=ah)
    @GetMapping(path="/search"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Medecin> medecins(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){

        Page<MedecinEntity> pageM = medecinService.getMedecinByName(keyword, PageRequest.of(page,size));
       return (Page<Medecin>) pageM.stream()
                .map(m->m.toMedecinDto()).collect(Collectors.toList());

    }

    @DeleteMapping("/{id}")
    // id est dans le path
    public void delete(@PathVariable Long id){
        medecinService.deleteMedecinById(id);
    }

    @PostMapping(path="/new"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin save(@RequestBody Medecin medecin){
        if (!medecinService.getMedecinById(medecin.getId()).isPresent()) {
            return medecinService.saveMedecin(medecin.toMedecinEntity()).toMedecinDto();
        }
        else{
            throw new RuntimeException("Add new Medecin with existing id is not possible");
        }
    }




}
