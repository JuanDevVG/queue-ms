package com.juandev.queuems.service;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.dto.PatientDTO;
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
    public Patient savePatient(PatientDTO patientDTO) {

        if (patientRepository.findByIdentityCard(patientDTO.getIdentityCard()).isPresent()){
            throw new ConflictIdentityCardException("Ya existe un paciente con la identificación proporcionada");
        } else {
            Patient newPatient = new Patient();
            newPatient.setIdentityCard(patientDTO.getIdentityCard());
            newPatient.setCategory(patientDTO.getCategoryId());

            // Guarda el nuevo paciente en la base de datos
            Patient savedPatient = patientRepository.save(newPatient);

            // Crea un nuevo DTO para el paciente creado y devuélvelo
            PatientDTO createdPatientDTO = new PatientDTO();
            createdPatientDTO.setIdentityCard(savedPatient.getIdentityCard());
            // Puedes copiar otros campos del paciente guardado al DTO aquí

            return createdPatientDTO;
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
        return patientRepository.save(newPatient);
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
