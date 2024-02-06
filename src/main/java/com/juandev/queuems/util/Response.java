package com.juandev.queuems.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String message;
    private Object newObject;

    public Response(String message, Object newObject){
        this.message = message;
        this.newObject = newObject;
    }
}
