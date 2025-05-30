package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class SchoolClassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int classId;

    private String name;

    @OneToMany(
        mappedBy = "schoolClass",
        fetch = FetchType.LAZY,
        targetEntity = PersonEntity.class
    )
    private Set<PersonEntity> persons = new HashSet<>();
}
