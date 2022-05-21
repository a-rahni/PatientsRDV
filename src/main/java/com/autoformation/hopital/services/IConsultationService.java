package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.ConsultationEntity;

import java.util.Optional;

public interface IConsultationService {
    ConsultationEntity saveConsultation(ConsultationEntity consultation);
    Optional<ConsultationEntity> getConsultationById(Long id);

    void deleteConsultationById(Long id);

}
