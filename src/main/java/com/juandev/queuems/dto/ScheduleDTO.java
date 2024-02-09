package com.juandev.queuems.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDTO {
    private Long scheduleId;
    private Long patientId;
    private Long userId;
    private String scheduleNumber;
    private LocalDateTime scheduleAssignmentTime;
    private LocalDateTime scheduleAttendanceTime;
    private String status;
}
