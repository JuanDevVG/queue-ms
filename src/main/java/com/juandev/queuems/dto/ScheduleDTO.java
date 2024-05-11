package com.juandev.queuems.dto;

import com.juandev.queuems.model.Patient;
import com.juandev.queuems.model.ServiceModel;
import com.juandev.queuems.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDTO {
    private Long scheduleId;
    private Patient patient;
    private User user;
    private String scheduleNumber;
    private LocalDateTime scheduleAssignmentDate;
    private LocalDateTime scheduleAttendanceDate;
    private String status;
    private ServiceModel service;
}
