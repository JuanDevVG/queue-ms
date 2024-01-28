package com.juandev.queuems.util;

public enum CategoryName {
    ADULTO_MAYOR("Adulto Mayor"),
    DISCAPACITADO("Persona con Discapacidad"),
    EMBARAZO("Mujer en estado de Gestacion");

    private final String description;
    CategoryName(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
