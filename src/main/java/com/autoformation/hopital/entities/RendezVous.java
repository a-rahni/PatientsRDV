package com.autoformation.hopital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)  // par defaut ordinal format
    private StatusRDV status;

    @ManyToOne(
            fetch = FetchType.LAZY//, // default value
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne(
            fetch = FetchType.LAZY
            //cascade = CascadeType.ALL
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="medecin_id")
    private Medecin medecin;

    @OneToOne(mappedBy = "rendezVous",
            cascade = CascadeType.ALL
    )
    private Consultation consultation;
}
