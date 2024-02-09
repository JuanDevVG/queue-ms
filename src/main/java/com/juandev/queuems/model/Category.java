package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "category")
    private List<Patient> patients;
}
