package com.juandev.queuems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String identityCard;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private boolean active;
}
