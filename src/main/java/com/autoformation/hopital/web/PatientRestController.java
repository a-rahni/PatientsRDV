package com.autoformation.hopital.web;

import com.autoformation.hopital.entities.Patient;
import com.autoformation.hopital.services.IPatientService;
import com.autoformation.hopital.services.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/API")
public class PatientRestController {

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IRendezVousService rendezVousService;

    @GetMapping("/")
    public String home(){
       return "redirect:/index"; // to define
    }

    @GetMapping("/patients/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient getPatient(@PathVariable Long id){
        return patientService.getPatientById(id).orElse(null);
    }

    @GetMapping(path = "/patients/all"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public List<Patient> patientList(){
        return ((List<Patient>) (patientService.getAllPatient()));
    }

    // methode recherche avec pagination exp  http://localhost:8086/patients?(page=1,size=5,keyword=ah)
    @GetMapping(path="/patients"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Page<Patient> Patients(
                         @RequestParam(name="page", defaultValue="0") int page,
                         @RequestParam(name="size",defaultValue="5") int size,
                         @RequestParam(name="keyword",defaultValue = "") String keyword){
        return  (Page<Patient>) patientService.getPatientByName(keyword, PageRequest.of(page,size));
    }

    @DeleteMapping("/patients/{id}")
    // id est dans le path
    public void delete(@PathVariable Long id){
        patientService.deletePatientById(id);
    }

    @PostMapping(path="/patients"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient save(@RequestBody Patient patient){
        if (!patientService.getPatientById(patient.getId()).isPresent()) {
            return patientService.savePatient(patient);
        }
        else{
            throw new RuntimeException("Add new Patient with existing id is not possible");
        }
    }


    @PutMapping(path="/patients/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable Long id){

        if (patientService.getPatientById(id).isPresent()) {
            patient.setId(id);
            return patientService.savePatient(patient);
        }
        else {
            throw new RuntimeException("Patient not found");
        }

    }

    @PatchMapping(path="/patients/{id}"/*, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public Patient updatePatientFields(@RequestBody Patient patient, @PathVariable Long id){
        if (patientService.getPatientById(id).isPresent()) {
            patient.setId(id);
            return patientService.savePatient(patient);
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
