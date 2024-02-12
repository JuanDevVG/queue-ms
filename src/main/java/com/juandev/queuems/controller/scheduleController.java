package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.RecordGetNotFoundException;
import com.juandev.queuems.dto.ScheduleDTO;
import com.juandev.queuems.service.ScheduleService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/schedule")
public class scheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create")
    private ResponseEntity<?> createSchedule (@RequestBody ScheduleDTO scheduleDTO){
        scheduleService.saveSchedule(scheduleDTO);
        return new ResponseEntity<>("Se creo el registro correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/get")
    private ResponseEntity<?> getAllSchedule (){
        try {
            return new ResponseEntity<>(scheduleService.getAllSchedule(), HttpStatus.OK);
        } catch (RecordGetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update")
    private ResponseEntity<?> updateSchedule (@RequestBody ScheduleDTO scheduleDTO){
        try {
            scheduleService.updateSchedule(scheduleDTO);
            return new ResponseEntity<>("Se actualizo el registro correctamente.", HttpStatus.OK);
        } catch (RecordGetNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
