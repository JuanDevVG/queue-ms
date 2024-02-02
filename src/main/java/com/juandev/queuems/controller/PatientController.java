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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<String> createPatient(@RequestBody NewPatientRequest request){

        try {
            patientService.savePatient(request);
            return new ResponseEntity<>("Se creo el paciente correctamente.", HttpStatus.CREATED);
        }
        catch (ConflictIdentityCardException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> listPatients = patientService.listPatients();

        if (!listPatients.isEmpty()){
            return ResponseEntity.ok(listPatients);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
