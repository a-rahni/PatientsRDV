package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Consultation;

import java.util.Optional;

public interface IConsultationService {
    Consultation saveConsultation(Consultation consultation);
    Optional<Consultation> getConsultationById(Long id);

    void deleteConsultationById(Long id);

}
