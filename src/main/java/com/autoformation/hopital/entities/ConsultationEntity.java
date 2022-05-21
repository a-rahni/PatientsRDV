package com.autoformation.hopital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="consultation")
public class ConsultationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private double prix_consultation;
    private String rapport;

    @OneToOne(
            //name ="consultation_rdv",
            fetch = FetchType.LAZY//,
            //cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            //cascade = CascadeType.ALL
    )
    @JoinColumn(name="rendezVous_id")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private RendezVousEntity rendezVous;
}
