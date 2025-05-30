package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Data
@Getter
@Setter
//@EqualsAndHashCode
public class CourseDto {

    private int courseId;

    private String name;

    private String fees;

    private Set<PersonDto> persons = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto courseDto = (CourseDto) o;
        return Objects.equals(name, courseDto.name) && Objects.equals(fees, courseDto.fees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fees);
    }
}
