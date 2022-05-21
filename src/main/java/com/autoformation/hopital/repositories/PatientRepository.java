package com.autoformation.hopital.repositories;

import com.autoformation.hopital.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,Long> {

    //Patient findById(Long Id);
    Collection<PatientEntity> findByNom(String name);

    Page<PatientEntity> findByNomContains(String kw, Pageable pageable);

    // JPQL  (pas de select)
    //@Query("FROM Patient WHERE name = ?1")
    //public Iterable<Patient> findByNameJPQL(String name);

    // SQL native

    //@Query(value = "SELECT * FROM patient WHERE dateNaissance = :dateN", nativeQuery = true)
    //public Iterable<Patient> findByCostNative(@Param("dateN") Date cost);




}
