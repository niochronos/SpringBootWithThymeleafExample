package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
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
        mappedBy = "schoolClassEntity",
        fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST,
        targetEntity = PersonEntity.class
    )
    private Set<PersonEntity> persons;
}
