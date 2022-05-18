package com.autoformation.hopital.web;

import com.autoformation.hopital.entities.Medecin;
import com.autoformation.hopital.entities.Patient;
import com.autoformation.hopital.services.IMedecinService;
import com.autoformation.hopital.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/API")
public class MedecinRestController {

    @Autowired
    private IMedecinService medecinService;

    @GetMapping("/medecins/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin getMedecin(@PathVariable Long id){
        return medecinService.getMedecinById(id).orElse(null);
    }

    @GetMapping(path = "/medecins/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Medecin> medecinList(){
        return ((List<Medecin>) (medecinService.getAllMedecin()));
    }

    // methode recherche avec pagination exp  http://localhost:8086/medecins?(page=1,size=5,keyword=ah)
    @GetMapping(path="/medecins"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Medecin> medecins(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){
        return  (Page<Medecin>) medecinService.getMedecinByName(keyword, PageRequest.of(page,size));
    }

    @DeleteMapping("/medecins/{id}")
    // id est dans le path
    public void delete(@PathVariable Long id){
        medecinService.deleteMedecinById(id);
    }

    @PostMapping(path="/medecins"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin save(@RequestBody Medecin medecin){
        if (!medecinService.getMedecinById(medecin.getId()).isPresent()) {
            return medecinService.saveMedecin(medecin);
        }
        else{
            throw new RuntimeException("Add new Medecin with existing id is not possible");
        }
    }


    @PutMapping(path="/medecins/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin updateMedecin(@RequestBody Medecin medecin, @PathVariable Long id){

        if (medecinService.getMedecinById(id).isPresent()) {
            medecin.setId(id);
            return medecinService.saveMedecin(medecin);
        }
        else {
            throw new RuntimeException("Medecin not found");
        }

    }

    @PatchMapping(path="/medecins/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Medecin updateMedecinFields(@RequestBody Medecin medecin, @PathVariable Long id){
        if (medecinService.getMedecinById(id).isPresent()) {
            medecin.setId(id);
            return medecinService.saveMedecin(medecin);
        }
        else {
            throw new RuntimeException("Medecin not found");
        }
    }

}
