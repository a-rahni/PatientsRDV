package com.autoformation.hopital.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//@XmlRootElement  // pour le mapping objet xml lib JAXON
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@Builder
@Table(name="patient")
@DynamicUpdate /* lors d'une modification, la requete sql ne reécrit que les champs modifié*/
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="dateNaissance") @Temporal(TemporalType.DATE)
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
    private Collection<RendezVous> rendezVous = new ArrayList<>();
    //private List<RendezVous> rendezVous;
    // quand fetch EAGER est utilisé , il faut initialiser (instancier) la collection
}
