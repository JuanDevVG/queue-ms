package com.juandev.queuems.util;

public enum ServiceType {
    PROGRAMACION_CIRUGIA("Pogramacion de Cirugia"),
    CITA_ESPECIALISTA("Cita con Especialista");

    private final String description;

    ServiceType(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
