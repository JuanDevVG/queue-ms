package com.juandev.queuems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
    private Long patientId;
    private String identityCard;
    private Long categoryId;
    private Long serviceId;
    private boolean active;
}
