package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.CourseDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.CourseEntity;

import java.util.stream.Collectors;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseDto mapToDto(CourseEntity entity) {
        return CourseMapper.mapToDto(entity, new CourseDto());
    }

    public static CourseDto mapToDto(CourseEntity entity, CourseDto dto) {
        dto.setCourseId(entity.getCourseId());
        dto.setName(entity.getName());
        dto.setFees(entity.getFees());
        dto.setPersons(
            entity.getPersons().stream()
                .map(PersonMapper::mapToDto)
                .collect(Collectors.toSet())
        );

        return dto;
    }

    public static CourseEntity mapToEntity(CourseDto dto) {
        return CourseMapper.mapToEntity(dto, new CourseEntity());
    }

    public static CourseEntity mapToEntity(CourseDto dto, CourseEntity entity) {
        entity.setCourseId(dto.getCourseId());
        entity.setName(dto.getName());
        entity.setFees(dto.getFees());
        entity.setPersons(
            dto.getPersons().stream()
                .map(PersonMapper::mapToEntity)
                .collect(Collectors.toSet())
        );

        return entity;
    }
}
