package com.juandev.queuems.service;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void savePatient(PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientRepository.findByIdentityCard(patientDTO.getIdentityCard());

        if (optionalPatient.isPresent()){
            throw new ConflictIdentityCardException("Ya existe un paciente con la identificación proporcionada");
        } else {
            Patient patient = Patient.builder()
                    .identityCard(patientDTO.getIdentityCard())
                    .name(patientDTO.getName())
                    .lastname(patientDTO.getLastname())
                    .category(patientDTO.getCategory())
                    .active(patientDTO.isActive())
                    .build();
            patientRepository.save(patient);
        }
    }

    public List<PatientDTO> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        if (!patients.isEmpty()){
            List<PatientDTO> patientDTOList = new ArrayList<>();

            for (Patient patient: patients){
                PatientDTO patientDTO = PatientDTO.builder()
                                .patientId(patient.getPatientId())
                                .identityCard(patient.getIdentityCard())
                                .name(patient.getName())
                                .lastname(patient.getLastname())
                                .category(patient.getCategory())
                                .active(patient.isActive())
                                .build();

                patientDTOList.add(patientDTO);
            }
            return patientDTOList;
        } else {
            throw new GetPatientNotFoundException("Aun no se encuentran pacientes en la base de datos");
        }

    }

    @Transactional
    public void updatePatient(PatientDTO patientDTO){
        //Verifica que el paciente se encuentre en la base de datos
        Patient patient = patientRepository.findById(patientDTO.getPatientId())
                .orElseThrow(() -> new GetPatientNotFoundException("El paciente con id: " + patientDTO.getPatientId()+ " No se ecuentra registrado"));

        Optional<Patient> patientWithIdentityCard = patientRepository.findByIdentityCard(patientDTO.getIdentityCard());

        // Verificar si la nueva identityCard ya está en uso por otro paciente
        if (patientWithIdentityCard.isPresent() && !patientWithIdentityCard.get().getPatientId().equals(patientDTO.getPatientId())) {
            throw new ConflictIdentityCardException("Ya existe un paciente con numero de identidad "+patientDTO.getIdentityCard());
        } else {
            //Asignar datos nuevos al paciente
            patient.setIdentityCard(patientDTO.getIdentityCard());
            patient.setName(patientDTO.getName());
            patient.setLastname(patientDTO.getLastname());
            patient.setCategory(patientDTO.getCategory());
            patient.setActive(patientDTO.isActive());

            patientRepository.save(patient);
        }
    }

    public PatientDTO getByIdentityCard(String identityCard) {
        //Verifica que el paciente se encuentre en la base de datos
        Patient patient = patientRepository.findByIdentityCard(identityCard)
                .orElseThrow(() -> new GetPatientNotFoundException("El paciente con id: " + identityCard+ " No se ecuentra registrado"));
        //Devuelve una instancia de PatientDTO con los datos del paciente
        return PatientDTO.builder()
                        .patientId(patient.getPatientId())
                        .identityCard(patient.getIdentityCard())
                        .name(patient.getName())
                        .lastname(patient.getLastname())
                        .category(patient.getCategory())
                        .active(patient.isActive())
                        .build();
    }
}
