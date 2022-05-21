package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.MedecinEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MedecinRepository extends JpaRepository<MedecinEntity,Long> {

    Collection<MedecinEntity> findByNom(String name);

    Page<MedecinEntity> findByNomContains(String kw, Pageable pageable);
}
