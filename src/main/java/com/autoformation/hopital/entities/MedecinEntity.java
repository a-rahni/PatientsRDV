package com.autoformation.hopital.entities;

import com.autoformation.hopital.dtos.Medecin;
import com.autoformation.hopital.dtos.RendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="medecin")
public class MedecinEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="dateNaissance")  @Temporal(TemporalType.DATE)  // garder que la date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @Column(name="adresse")
    private String adresse;
    @Column(name="email")
    private String email;
    private String specialite;

    //@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @OneToMany(
            mappedBy = "medecin",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            //orphanRemoval = true
    )
    // JPA EntityManagerFactory: Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn
    private Collection<RendezVousEntity> rendezVous = new ArrayList();

    public Medecin toMedecin(){
        Medecin medecin= new Medecin();

        medecin.setId(this.id);
        medecin.setNom(this.nom);
        medecin.setPrenom(this.prenom);
        medecin.setAdresse(this.adresse);
        medecin.setEmail(this.email);
        medecin.setDateNaissance(this.dateNaissance);
        medecin.setSpecialite(this.specialite);

        List<RendezVous> rdv = new ArrayList<RendezVous>() ;
        /** to avoid cyclique dependance for getMedecin */
//        for(RendezVousEntity r:rendezVous){
//            rdv.add(r.toRendezVousDto());
//        }
//        m.setRendezVous(rdv);
        return medecin;
    }
}
