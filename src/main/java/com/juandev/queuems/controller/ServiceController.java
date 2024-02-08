package com.juandev.queuems.controller;

import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.service.ServiceModelService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceModelService serviceModelService;

    @PostMapping("/create")
    public ResponseEntity<Response> createService (@RequestBody ServiceModel newService){
        return new ResponseEntity<>(new Response("Servicio creado con exito", serviceModelService.createService(newService)), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ServiceModel>> getAllService (){
        return new ResponseEntity<>(serviceModelService.getAllService(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateService (@RequestBody ServiceModel updateService){
        return new ResponseEntity<>(new Response("Se actualizo el registro correctamente", serviceModelService.updateService(updateService)), HttpStatus.OK);
    }
}
