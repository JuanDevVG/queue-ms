package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juandev.queuems.util.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "services")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long serviceId;

    @Column(name = "service_type")

    private String serviceType;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<Patient> patients;
}
