package com.juandev.queuems.model;

import com.juandev.queuems.util.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long scheduleId;

    @OneToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "schedule_number")
    private String scheduleNumber;

    @Column(name = "schedule_assignment_time")
    private LocalDateTime scheduleAssignmentTime;

    @Column(name = "schedule_attendance_time")
    private LocalDateTime scheduleAttendanceTime;

    @Column(name = "status")
    //@Enumerated(EnumType.STRING)
    private String status;
}
