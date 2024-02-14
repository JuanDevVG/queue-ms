package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.ConflictRecordException;
import com.juandev.queuems.Exception.RecordGetNotFoundException;
import com.juandev.queuems.dto.ServiceDTO;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.service.ServiceModelService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceModelService serviceModelService;

    @PostMapping("/create")
    public ResponseEntity<String> createService (@RequestBody ServiceDTO serviceDTO){
        try {
            serviceModelService.saveService(serviceDTO);
            return new ResponseEntity<>("Servicio creado con exito", HttpStatus.CREATED);
        } catch (ConflictRecordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllService (){
        try {
            return new ResponseEntity<>(serviceModelService.getAllService(), HttpStatus.OK);
        } catch (RecordGetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateService (@RequestBody ServiceDTO serviceDTO){
        try {
            serviceModelService.updateService(serviceDTO);
            return new ResponseEntity<>("Se actualizo el registro correctamente", HttpStatus.OK);
        } catch (RecordGetNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
