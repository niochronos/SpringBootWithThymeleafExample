package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "class")
public class SchoolClassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @OneToMany(
        mappedBy = "schoolClassEntity",
        fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST,
        targetEntity = PersonEntity.class
    )
    private Set<PersonEntity> persons;
}
