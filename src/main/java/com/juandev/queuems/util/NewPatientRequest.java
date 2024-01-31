package com.juandev.queuems.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewPatientRequest {

    private String identityCard;

    private CategoryName category;

    private ServiceType service;

}
