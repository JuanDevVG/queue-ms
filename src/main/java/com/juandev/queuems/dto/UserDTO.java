package com.juandev.queuems.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String identityCard;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private boolean active;
    private String role;
}
