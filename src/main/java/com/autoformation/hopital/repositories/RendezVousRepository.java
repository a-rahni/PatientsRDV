package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    public Collection<RendezVous> findByPatient(Long id);
}
