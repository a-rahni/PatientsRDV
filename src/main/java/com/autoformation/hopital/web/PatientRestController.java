package com.autoformation.hopital.web;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.services.IPatientService;
import com.autoformation.hopital.services.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

        // at first time, I do the mapping manually. to use mapstruct later
       Optional<PatientEntity> pEntity= patientService.getPatientById(id);
       if(pEntity.isPresent())
       {
          return pEntity.get().toPatientDto();
       }
       else
       {
           return null;
       }
    }

    @GetMapping(path = "/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Patient> patientList(){
        return patientService.getAllPatient().stream().map(p->p.toPatientDto()).collect(Collectors.toList());
    }

    // methode recherche avec pagination exp  http://localhost:8086/patients?(page=1,size=5,keyword=ah)
    @GetMapping(path="/search"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Patient> Patients(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){
        return  (Page<Patient>)patientService.getPatientByName(keyword, PageRequest.of(page,size)).stream()
                .map(p->p.toPatientDto()).collect(Collectors.toList());

    }

    @DeleteMapping("/{id}")
    // id est dans le path
    public void delete(@PathVariable Long id){
        patientService.deletePatientById(id);
    }

    @PostMapping(path="/new"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient save(@RequestBody Patient patient){
        PatientEntity p = patient.toPatientEntity();
        if (!patientService.getPatientById(p.getId()).isPresent()) {
            return patientService.savePatient(p).toPatientDto();
        }
        else{
            throw new RuntimeException("Add new Patient with existing id is not possible");
        }
    }


    @PutMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable Long id){

        if (patientService.getPatientById(id).isPresent()) {
            patient.setId(id);
            return patientService.savePatient(patient.toPatientEntity()).toPatientDto();
        }
        else {
            throw new RuntimeException("Patient not found");
        }

    }

    @PatchMapping(path="/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatientFields(@RequestBody Patient patient, @PathVariable Long id){
        if (patientService.getPatientById(id).isPresent()) {
            patient.setId(id);
            return patientService.savePatient(patient.toPatientEntity()).toPatientDto();
        }
        else {
            throw new RuntimeException("Patient not found");
        }
    }

//    /*   Rendez vous par patient*/
//    @GetMapping(path="/patients/test"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
//    public List<Patient> patientListtest(){
//        return ((List<Patient>) (patientService.getAllPatient()));
//    }
//    //public List<RendezVous> getRdvPatient(@PathVariable Long id, @RequestParam(name="date", defaultValue="new Date()") Date date){
////    public List<Patient> getRdvPatient(/*@PathVariable Long id*/) {
//////        return (List<RendezVous>) patientService.getRendezVousByPatient(id);
//////    }
////        return ((List<Patient>) (patientService.getAllPatient()));
////    }

}
