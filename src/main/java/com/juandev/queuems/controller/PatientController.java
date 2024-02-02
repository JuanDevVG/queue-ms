package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.InvalidCategoryOrServiceException;
import com.juandev.queuems.Exception.ConflictIdentityCardException;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.service.PatientService;
import com.juandev.queuems.util.NewPatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<String> createPatient(@RequestBody NewPatientRequest request){

        try {
            Patient newPatient = patientService.savePatient(request);
            return new ResponseEntity<>("Se creo el usuario correctamente.", HttpStatus.CREATED);
        }
        catch (ConflictIdentityCardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
