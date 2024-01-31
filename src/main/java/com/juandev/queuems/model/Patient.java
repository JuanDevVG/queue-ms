package com.juandev.queuems.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long patientId;

    @Getter @Setter @Column(name = "identity_card")
    private String identityCard;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "service_id")
    private ServiceModel service;

    @OneToOne
    @JoinColumn(name = "id")
    @Getter @Setter
    private Schedule schedule;
}
