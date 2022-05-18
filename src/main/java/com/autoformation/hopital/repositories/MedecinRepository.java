package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {

    Collection<Medecin> findByNom(String name);

    Page<Medecin> findByNomContains(String kw, Pageable pageable);
}
