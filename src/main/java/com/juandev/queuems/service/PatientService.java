package com.juandev.queuems.service;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.repository.PatientRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Patient savePatient(Patient patient) {
        if (patientRepository.findByIdentityCard(patient.getIdentityCard()).isPresent()){
            throw new ConflictIdentityCardException("Ya existe un paciente con la identificación proporcionada");
        } else {
            return patientRepository.save(patient);
        }
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        if (!patients.isEmpty()){
            return patients;
        } else {
            throw new GetPatientNotFoundException("Aun no se encuentran pacientes en la base de datos");
        }

    }

    @Transactional
    public Patient updatePatient(Patient newPatient){
        Patient existingPatient = patientRepository.findById(newPatient.getPatientId())
                .orElseThrow(() -> new GetPatientNotFoundException("El paciente con id: " + newPatient.getPatientId()+ " No se ecuentra registrado"));

        // Verificar si la nueva identityCard ya está en uso por otro paciente
        Optional<Patient> patientWithIdentityCard = patientRepository.findByIdentityCard(newPatient.getIdentityCard());

        if (patientWithIdentityCard.isPresent() && !patientWithIdentityCard.get().getPatientId().equals(newPatient.getPatientId())) {
            throw new ConflictIdentityCardException("Ya existe un paciente con numero de identidad "+newPatient.getIdentityCard());
        }

        // Actualizar los campos del paciente existente con los nuevos datos
        existingPatient.setIdentityCard(newPatient.getIdentityCard());
        existingPatient.setCategory(newPatient.getCategory());
        existingPatient.setService(newPatient.getService());
        existingPatient.setActive(newPatient.isActive());
        existingPatient.setSchedule(newPatient.getSchedule());

        // Guardar el paciente actualizado en la base de datos
        return patientRepository.save(existingPatient);
    }

    public Patient getByIdentityCard(String identityCard) {
        Optional<Patient> patient = patientRepository.findByIdentityCard(identityCard);
        if (patient.isPresent()){
            return patient.get();
        } else {
            throw new GetPatientNotFoundException("El paciente con numero de identificacion "+identityCard+" no se encuentra en la base de datos");
        }
    }
}
