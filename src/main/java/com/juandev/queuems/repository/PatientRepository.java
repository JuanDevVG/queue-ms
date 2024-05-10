package com.juandev.queuems.repository;


import com.juandev.queuems.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> findByIdentityCard(String identityCard);
}
