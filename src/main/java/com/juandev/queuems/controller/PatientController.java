package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.service.PatientService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody PatientDTO patientDTO) {
        try {
            patientService.savePatient(patientDTO);
            return new ResponseEntity<>("El paciente se creo correctamente.", HttpStatus.CREATED);
        } catch (ConflictIdentityCardException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPatients() {
        try {
            List<PatientDTO> patientDTOList = patientService.getAllPatients();
            return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
        } catch (GetPatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{identityCard}")
    public ResponseEntity<?> getPatientByIdentityCard(@PathVariable String identityCard) {
        try {
            PatientDTO patientDTO = patientService.getByIdentityCard(identityCard);
            return new ResponseEntity<>(new Response("Busqueda exitosa", patientDTO), HttpStatus.OK);
        } catch (GetPatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO) {
        try {
            patientService.updatePatient(patientDTO);
            return new ResponseEntity<>("Se actualizo el registro correctamente", HttpStatus.OK);
        } catch (GetPatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConflictIdentityCardException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
}