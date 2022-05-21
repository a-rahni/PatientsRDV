package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.ConsultationEntity;
import com.autoformation.hopital.repositories.ConsultationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ConsultationServiceImpl implements IConsultationService {
    private ConsultationRepository consultationRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public ConsultationEntity saveConsultation(ConsultationEntity consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Optional<ConsultationEntity> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public void deleteConsultationById(Long id) {
        consultationRepository.deleteById(id);
    }
}
