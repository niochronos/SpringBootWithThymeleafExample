package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CourseDto {

    private int courseId;

    private String name;

    private String fees;

    private Set<PersonDto> persons = new HashSet<>();
}
