package com.juandev.queuems.repository;


import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> findByIdentityCard(String identityCard);
}
