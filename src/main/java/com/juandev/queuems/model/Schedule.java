package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "schedule_assignment_date")
    private LocalDateTime scheduleAssignmentDate;

    @Column(name = "schedule_attendance_date")
    private LocalDateTime scheduleAttendanceDate;

    @Column(name = "status")
    //@Enumerated(EnumType.STRING)
    private String status;
}
