package com.autoformation.hopital.web;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import com.autoformation.hopital.services.IMedecinService;
import com.autoformation.hopital.services.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rdv")
public class RdvRestController {

    @Autowired
    private IRendezVousService rendezVousService;


    @DeleteMapping("/{id}")
    // id est dans le path
    public void delete(@PathVariable Long id){
        rendezVousService.deleteRDVById(id);
    }

    /**   to thing how to insert a new RDV.
     * use case: create an open rdv for medecin
     * and when Patient choice an Opened rdv. the RDV will be updated by adding the Patient*/
    @PostMapping(path="/new"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public RendezVous save(@RequestBody RendezVous rendezVous){
        

        return new RendezVous();
    }



}
