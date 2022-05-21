package com.autoformation.hopital.dtos;

import com.autoformation.hopital.entities.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    private Long id;
    private Date date;
    private StatusRDV status;
    private Patient patient;
    private Medecin medecin;
    private Consultation consultation;

    public RendezVousEntity toRendezVousEntity(){
        RendezVousEntity rdv = new RendezVousEntity();
        rdv.setId(this.id);
        if(this.medecin != null)   rdv.setMedecin(this.medecin.toMedecinEntity());
        if(this.patient != null)   rdv.setPatient(this.patient.toPatientEntity());
        rdv.setDate(this.date);
        rdv.setStatus(this.status);
        //rdv.setConsultation(this.consultation);

        return rdv;
    }
}
