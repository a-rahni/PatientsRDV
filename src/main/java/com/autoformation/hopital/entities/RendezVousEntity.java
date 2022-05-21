package com.autoformation.hopital.entities;

import com.autoformation.hopital.dtos.RendezVous;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="RendezVous")
public class RendezVousEntity {
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
    private PatientEntity patient;

    @ManyToOne(
            fetch = FetchType.LAZY
            //cascade = CascadeType.ALL
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="medecin_id")
    private MedecinEntity medecin;

    @OneToOne(mappedBy = "rendezVous",
            cascade = CascadeType.ALL
    )
    private ConsultationEntity consultation;

    public RendezVous toRendezVousDto(){
        RendezVous rdv = new RendezVous();
        rdv.setId(this.id);
        //if(this.medecin != null)   rdv.setMedecin(this.medecin.toMedecinDto());
        //if(this.patient != null)   rdv.setPatient(this.patient.toPatientDto());
        rdv.setDate(this.date);
        rdv.setStatus(this.status);
        //rdv.setConsultation(this.consultation);

        return rdv;
    }
}
