package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<PersonEntity> persons = new HashSet<>();
}
