package com.autoformation.hopital.dtos;

import com.autoformation.hopital.entities.RendezVousEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
public class Consultation {
    private Long id;
    private Date dateConsultation;
    private double prix_consultation;
    private String rapport;
    private RendezVous rendezVous;
}
