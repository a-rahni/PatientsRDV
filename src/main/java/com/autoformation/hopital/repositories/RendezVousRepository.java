package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVousEntity,Long> {
    public Collection<RendezVousEntity> findByPatient(Long id);
}
