package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
}
