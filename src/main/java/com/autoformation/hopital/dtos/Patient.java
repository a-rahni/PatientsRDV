package com.autoformation.hopital.dtos;

import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {

    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String email;
    private Boolean malade;
    private Collection<RendezVous> rendezVous = new ArrayList<>();

    /** to use mapStructs later for mapping entity-dto */
    public PatientEntity toPatientEntity(){
        PatientEntity p= new PatientEntity();

            p.setId(this.id);
            p.setNom(this.nom);
            p.setPrenom(this.prenom);
            p.setAdresse(this.adresse);
            p.setEmail(this.email);
            p.setDateNaissance(this.dateNaissance);
            p.setMalade(this.malade);

            List<RendezVousEntity> rdv = new ArrayList<RendezVousEntity>() ;
            for(RendezVous r:rendezVous){
                rdv.add(r.toRendezVousEntity());
            }
            p.setRendezVous(rdv);
            return p;
    }


}
