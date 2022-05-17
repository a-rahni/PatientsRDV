package com.autoformation.hopital.services;

import com.autoformation.hopital.entities.Consultation;
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
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Optional<Consultation> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public void deleteConsultationById(Long id) {
        consultationRepository.deleteById(id);
    }
}
