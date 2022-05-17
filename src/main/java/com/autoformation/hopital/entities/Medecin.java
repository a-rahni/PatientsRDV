package com.autoformation.hopital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="medecin")
public class Medecin {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nom", unique = true)
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="dateNaissance")  @Temporal(TemporalType.DATE)  // garder que la date
    private Date dateNaissance;
    @Column(name="adresse")
    private String adresse;
    @Column(name="email")
    private String email;
    private String specialite;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @OneToMany(
            mappedBy = "medecin",
            fetch = FetchType.LAZY//,
            //cascade = CascadeType.ALL,
            //orphanRemoval = true
    )
    // JPA EntityManagerFactory: Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn
    private Collection<RendezVous> rendezVous = new ArrayList();
}
