package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juandev.queuems.util.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "services")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long serviceId;

    @Column(name = "service_type")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service")
    @Getter @Setter @JsonIgnore
    private List<Patient> patients;
}
