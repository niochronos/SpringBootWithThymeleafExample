package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.CourseDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.CourseEntity;

import java.util.stream.Collectors;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseDto toDto(CourseEntity entity) {
        return CourseMapper.toDto(entity, new CourseDto());
    }

    public static CourseDto toDto(CourseEntity entity, CourseDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setCourseId(entity.getCourseId());
        dto.setName(entity.getName());
        dto.setFees(entity.getFees());
        if (entity.getPersons() != null) {
            dto.setPersons(
                entity.getPersons().stream()
                    .map(PersonMapper::toDto)
                    .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static CourseEntity toEntity(CourseDto dto) {
        return CourseMapper.toEntity(dto, new CourseEntity());
    }

    public static CourseEntity toEntity(CourseDto dto, CourseEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setCourseId(dto.getCourseId());
        entity.setName(dto.getName());
        entity.setFees(dto.getFees());
        if (dto.getPersons() != null) {
            entity.setPersons(
                dto.getPersons().stream()
                    .map(PersonMapper::toEntity)
                    .collect(Collectors.toSet())
            );
        }

        return entity;
    }
}
