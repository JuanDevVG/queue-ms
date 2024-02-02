package com.juandev.queuems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juandev.queuems.util.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categories")
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long categoryId;

    @Column(name = "category_name")
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "category")
    @Getter @Setter @JsonIgnore
    private List<Patient> patients;
}
