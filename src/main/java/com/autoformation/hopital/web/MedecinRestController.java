package com.autoformation.hopital.web;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.StatusRDV;
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
        return  medecinService.getMedecinById(id).orElse(null);
    }

    @GetMapping(path = "/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Medecin> medecinsList(){
        return medecinService.getAllMedecin();
    }

    // methode recherche avec pagination exp  http://localhost:8086/medecins?(page=1,size=5,keyword=ah)
    @GetMapping(path="/search"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Medecin> getMedecins(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){

        Page<Medecin> pageMedecin = medecinService.getMedecinByName(keyword, PageRequest.of(page,size));
       return pageMedecin;
    }

    @DeleteMapping("/{id}")
    // id est dans le path
    public void deleteMedecin(@PathVariable Long id){
        medecinService.deleteMedecinById(id);
    }

    @PostMapping(path="/new"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin saveMedecin(@RequestBody Medecin medecin){
        return medecinService.saveMedecin(medecin);
    }

    @PutMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin updateMedecin(@RequestBody Medecin medecin, @PathVariable Long id){
        return medecinService.updateMedecin(medecin, id);
    }

    @PatchMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin updateMedecinFields(@RequestBody Medecin medecin, @PathVariable Long id){
        return medecinService.updateMedecin(medecin, id);
    }

    /** les RendezVous ouvert d'un  Medecin*/

    @GetMapping(path="/{id}/rdv")
    List<RendezVous>  getRdvMedecin(@PathVariable Long id,
                                    @RequestParam(name="status", defaultValue="") StatusRDV status){
        return medecinService.getRdvMedecin(id,status);
    }

    @PostMapping(path="/{id}/openRdv")
    RendezVous  openRdvMedecin(@PathVariable Long id,@RequestBody RendezVous rendezVous){
        return medecinService.openRdvMedecin(id,rendezVous);
    }

}
