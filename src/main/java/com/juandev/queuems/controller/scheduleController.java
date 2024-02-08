package com.juandev.queuems.controller;

import com.juandev.queuems.model.Schedule;
import com.juandev.queuems.service.ScheduleService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/schedule")
public class scheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create")
    private ResponseEntity<Schedule> createSchedule (@RequestBody Schedule newSchedule){
        return new ResponseEntity<>(scheduleService.createSchedule(newSchedule), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    private ResponseEntity<List<Schedule>> getAllSchedule (){
        return new ResponseEntity<>(scheduleService.getAllSchedule(), HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<Response> updateSchedule (@RequestBody Schedule newSchedule){
        return new ResponseEntity<>(new Response("Se actualizo el registro correctamente.", scheduleService.updateSchedule(newSchedule)), HttpStatus.OK);
    }
}
