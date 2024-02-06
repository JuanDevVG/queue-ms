package com.juandev.queuems.service;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.repository.PatientRepository;
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
    public void updatePatient(Patient newPatient){
        Optional<Patient> optionalPatient = patientRepository.findById(newPatient.getPatientId());

        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setIdentityCard(newPatient.getIdentityCard());
            existingPatient.setService(newPatient.getService());
            existingPatient.setActive(newPatient.isActive());
            existingPatient.setCategory(newPatient.getCategory());

            try {
                patientRepository.save(existingPatient);
            } catch (DuplicateKeyException e) {
                // Manejo específico para violación de restricción de unicidad
                throw new DataIntegrityViolationException("El numero de identificacion "+newPatient.getIdentityCard()+" pertenece a otro paciente registrado");
            }
        } else {
            // Manejo si el paciente no existe
            throw new GetPatientNotFoundException("El paciente no se encuentra registrado");
        }

    }

    @Transactional
    public Patient inactivatePatient(Patient patient){
        patient.setActive(false);
        return patientRepository.save(patient);
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
