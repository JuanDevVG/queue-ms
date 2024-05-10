package com.juandev.queuems.service;

import com.juandev.queuems.Exception.RecordGetNotFoundException;
import com.juandev.queuems.dto.ScheduleDTO;
import com.juandev.queuems.model.Schedule;
import com.juandev.queuems.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void saveSchedule (ScheduleDTO scheduleDTO){

        Schedule newSchedule = Schedule.builder()
                        .scheduleNumber(scheduleDTO.getScheduleNumber())
                        .patient(scheduleDTO.getPatient())
                        .user(scheduleDTO.getUser())
                        .status(scheduleDTO.getStatus())
                        .scheduleAssignmentDate(scheduleDTO.getScheduleAssignmentDate())
                        .scheduleAttendanceDate(scheduleDTO.getScheduleAttendanceDate())
                        .build();

        scheduleRepository.save(newSchedule);
    }

    public List<ScheduleDTO> getAllSchedule (){
        List<Schedule> schedules = scheduleRepository.findAll();

        if (!schedules.isEmpty()){
            List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

            for (Schedule schedule : schedules) {

                //Mapea datos de schedules en lista de ScheduleDTO
                ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                                .scheduleId(schedule.getScheduleId())
                                .patient(schedule.getPatient())
                                .user(schedule.getUser())
                                .scheduleNumber(schedule.getScheduleNumber())
                                .scheduleAssignmentDate(schedule.getScheduleAssignmentDate())
                                .scheduleAttendanceDate(schedule.getScheduleAttendanceDate())
                                .status(schedule.getStatus())
                                .build();

                //Agrega la CategoryDTO creada a la lista de CategoryDTOList
                scheduleDTOList.add(scheduleDTO);

            }
            return scheduleDTOList;
        } else {
            throw new RecordGetNotFoundException("Aun no se encuentran turnos registrados en la base de datos");
        }
    }

    public void updateSchedule (ScheduleDTO scheduleDTO){
        //Verifica que el turno se encuentre en la base de datos
        Schedule schedule = scheduleRepository.findById(scheduleDTO.getScheduleId())
                .orElseThrow(() -> new RecordGetNotFoundException("El turno con id: " + scheduleDTO.getScheduleId()+ " No se ecuentra registrado"));

        schedule.setScheduleNumber(scheduleDTO.getScheduleNumber());
        schedule.setStatus(scheduleDTO.getStatus());
        schedule.setScheduleAttendanceDate(scheduleDTO.getScheduleAttendanceDate());
        schedule.setScheduleAssignmentDate(scheduleDTO.getScheduleAssignmentDate());

        scheduleRepository.save(schedule);
    }

    public List<ScheduleDTO> getScheduleByStatus (String status){
        List<Schedule> schedules = scheduleRepository.findByStatus(status).orElseThrow();

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (Schedule schedule : schedules) {

            //Mapea datos de schedules en lista de ScheduleDTO
            ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                    .scheduleId(schedule.getScheduleId())
                    .patient(schedule.getPatient())
                    .user(schedule.getUser())
                    .scheduleNumber(schedule.getScheduleNumber())
                    .scheduleAssignmentDate(schedule.getScheduleAssignmentDate())
                    .scheduleAttendanceDate(schedule.getScheduleAttendanceDate())
                    .status(schedule.getStatus())
                    .build();

            //Agrega la CategoryDTO creada a la lista de CategoryDTOList
            scheduleDTOList.add(scheduleDTO);

        }
        return scheduleDTOList;
    }
}
