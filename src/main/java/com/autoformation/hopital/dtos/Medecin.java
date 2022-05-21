package com.autoformation.hopital.dtos;

import com.autoformation.hopital.entities.MedecinEntity;
import com.autoformation.hopital.entities.PatientEntity;
import com.autoformation.hopital.entities.RendezVousEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin {
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String email;
    private String specialite;
    private Collection<RendezVous> rendezVous = new ArrayList();

    public MedecinEntity toMedecinEntity(){
        MedecinEntity m= new MedecinEntity();

        m.setId(this.id);
        m.setNom(this.nom);
        m.setPrenom(this.prenom);
        m.setAdresse(this.adresse);
        m.setEmail(this.email);
        m.setDateNaissance(this.dateNaissance);
        m.setSpecialite(this.specialite);

        List<RendezVousEntity> rdv = new ArrayList<RendezVousEntity>() ;
        for(RendezVous r:rendezVous){
            rdv.add(r.toRendezVousEntity());
        }
        m.setRendezVous(rdv);
        return m;
    }
}
