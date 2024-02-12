package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    @JoinColumn(name = "active")
    private boolean active;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id")
    private Schedule schedule;
}
