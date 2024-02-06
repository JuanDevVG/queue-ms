package com.juandev.queuems.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@ToString
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long patientId;

    @Column(name = "identity_card")
    private String identityCard;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    @JoinColumn(name = "active")
    private boolean active;

    @OneToOne
    @JoinColumn(name = "id")
    private Schedule schedule;
}
