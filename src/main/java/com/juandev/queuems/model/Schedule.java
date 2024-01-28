package com.juandev.queuems.model;

import com.juandev.queuems.util.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id")
    private int scheduleId;

    @OneToOne()
    @Getter @Setter @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne()
    @Getter @Setter
    @JoinColumn(name = "user_id")
    private User user;

    @Getter @Setter @Column(name = "schedule_number")
    private String scheduleNumber;

    @Getter @Setter @Column(name = "schedule_assignment_time")
    private LocalDateTime scheduleAssignmentTime;

    @Getter @Setter @Column(name = "schedule_attendance_time")
    private LocalDateTime scheduleAttendanceTime;

    @Getter @Setter @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
}
