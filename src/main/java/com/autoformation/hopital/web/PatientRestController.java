package com.autoformation.hopital.web;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.StatusRDV;
import com.autoformation.hopital.services.IPatientService;
import com.autoformation.hopital.services.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientRestController {

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IRendezVousService rendezVousService;

    @GetMapping("/")
    public String home(){
       return "redirect:/index"; // to define
    }

    @GetMapping("/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient getPatient(@PathVariable Long id){
       return patientService.getPatientById(id).orElse(null);
    }

    @GetMapping(path = "/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Patient> patientsList(){
        return patientService.getAllPatient();
    }

    // methode recherche avec pagination exp  http://localhost:8086/patients?(page=1,size=5,keyword=ah)
    @GetMapping(path="/search"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Patient> getPatientsByName(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){
        return  patientService.getPatientByName(keyword, PageRequest.of(page,size));
    }

    @DeleteMapping("/{id}")
    // id est dans le path
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatientById(id);
    }

    @PostMapping(path="/new"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @PutMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable Long id){
        return patientService.updatePatient(patient, id);
    }

    @PatchMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatientFields(@RequestBody Patient patient, @PathVariable Long id){
        return patientService.updatePatient(patient, id);
    }
    /** les RendezVous ouvert d'un  Medecin*/

    @GetMapping(path="/{id}/rdv")
    List<RendezVous>  getRdvPatient(@PathVariable Long id,
                                    @RequestParam(name="status", defaultValue="") StatusRDV status){
        return patientService.getRdvPatient(id,status);
    }

}
