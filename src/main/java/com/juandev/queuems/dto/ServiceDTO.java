package com.juandev.queuems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {

    private Long serviceId;
    private String serviceType;
    private boolean active;

}
