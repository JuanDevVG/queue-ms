package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@ToString
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter @Setter @Column(name = "id")
    private Long userId;

    @Getter @Setter @Column(name = "identity_card")
    private String identityCard;

    @Getter @Setter @Column(name = "first_name")
    private String firstName;

    @Getter @Setter @Column(name = "last_name")
    private String lastName;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;

    @Getter @Setter @Column(name = "username")
    private String username;

    @Getter @Setter @Column(name = "active")
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private List<Schedule> schedules;

}
