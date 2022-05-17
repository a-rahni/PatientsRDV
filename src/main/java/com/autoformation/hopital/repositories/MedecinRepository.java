package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {

    Iterable<Medecin> findByNom(String name);
}
