package com.autoformation.hopital.entities;

import com.autoformation.hopital.dtos.Patient;
import com.autoformation.hopital.dtos.RendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//@XmlRootElement  // pour le mapping objet xml lib JAXON
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@Builder
@Table(name="patient")
@DynamicUpdate /* lors d'une modification, la requete sql ne reécrit que les champs modifié*/
public class PatientEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="dateNaissance") @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @Column(name="adresse")
    private String adresse;
    @Column(name="email")
    private String email;
    @Column(name="malade")
    private Boolean malade;
    @OneToMany(
            mappedBy = "patient",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            //orphanRemoval = true
    )
    //JPA EntityManagerFactory: Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn
    private Collection<RendezVousEntity> rendezVous = new ArrayList<>();
    // quand fetch EAGER est utilisé , il faut initialiser (instancier) la collection

    public Patient toPatient(){
        Patient patient= new Patient();

        patient.setId(this.id);
        patient.setNom(this.nom);
        patient.setPrenom(this.prenom);
        patient.setAdresse(this.adresse);
        patient.setEmail(this.email);
        patient.setDateNaissance(this.dateNaissance);
        patient.setMalade(this.malade);

        List<RendezVous> rdv = new ArrayList<RendezVous>() ;
//        for(RendezVousEntity r:rendezVous){
//            rdv.add(r.toRendezVousDto());
//        }
//        p.setRendezVous(rdv);
        return patient;
    }
}
