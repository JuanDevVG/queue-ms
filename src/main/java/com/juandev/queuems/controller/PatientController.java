package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.dto.PatientDTO;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.service.PatientService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<Response> createPatient(@RequestBody PatientDTO patientDTO) {

        try {
            Response response = new Response("El paciente se creo correctamente.", patientService.savePatient(patientDTO));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ConflictIdentityCardException e) {
            Response response = new Response(e.getMessage(), patientDTO);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPatients() {
        try {
            List<Patient> listPatients = patientService.getAllPatients();
            return new ResponseEntity<>(listPatients, HttpStatus.OK);
        } catch (GetPatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{identityCard}")
    public ResponseEntity<?> getPatientByIdentityCard(@PathVariable String identityCard) {
        try {
            Patient patient = patientService.getByIdentityCard(identityCard);
            return new ResponseEntity<>(new Response("Busqueda exitosa", patient), HttpStatus.OK);
        } catch (GetPatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updatePatient(@RequestBody Patient patient) {
        Patient updatedPatiente = patientService.updatePatient(patient);
        return new ResponseEntity<>(new Response("Se actualizo el registro correctamente", updatedPatiente), HttpStatus.OK);
    }
}