package com.juandev.queuems.service;

import com.juandev.queuems.model.Schedule;
import com.juandev.queuems.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule (Schedule newSchedule){
        return scheduleRepository.save(newSchedule);
    }

    public List<Schedule> getAllSchedule (){
        return scheduleRepository.findAll();
    }

    public Schedule updateSchedule (Schedule newSchedule){
        return scheduleRepository.save(newSchedule);
    }
}
