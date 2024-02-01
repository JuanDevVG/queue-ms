package com.juandev.queuems.controller;

import com.juandev.queuems.model.Patient;
import com.juandev.queuems.service.PatientService;
import com.juandev.queuems.util.CategoryName;
import com.juandev.queuems.util.NewPatientRequest;
import com.juandev.queuems.util.ServiceType;
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
            //Validar que la categoria recibida exista en el enum CategoryName Y ServiceType
            CategoryName categoryName = CategoryName.valueOf(request.getCategory().name());
            ServiceType serviceType = ServiceType.valueOf(request.getService().name());
            patientService.addPatient(request);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }

    }
}
