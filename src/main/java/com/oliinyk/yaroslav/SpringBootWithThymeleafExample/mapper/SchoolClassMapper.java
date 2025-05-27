package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.SchoolClassDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.SchoolClassEntity;

import java.util.stream.Collectors;

public class SchoolClassMapper {

    private SchoolClassMapper() {}

    public static SchoolClassDto mapToDto(SchoolClassEntity entity) {
        return mapToDto(entity, new SchoolClassDto());
    }

    public static SchoolClassDto mapToDto(SchoolClassEntity entity, SchoolClassDto dto) {
        dto.setClassId(entity.getClassId());
        dto.setName(entity.getName());
        dto.setPersons(
            entity.getPersons().stream()
                .map(PersonMapper::mapToDto)
                .collect(Collectors.toSet())
        );

        return dto;
    }

    public static SchoolClassEntity mapToEntity(SchoolClassDto dto) {
        return mapToEntity(dto, new SchoolClassEntity());
    }

    public static SchoolClassEntity mapToEntity(SchoolClassDto dto, SchoolClassEntity entity) {
        entity.setClassId(dto.getClassId());
        entity.setName(dto.getName());
        entity.setPersons(
            dto.getPersons().stream()
                .map(PersonMapper::mapToEntity)
                .collect(Collectors.toSet())
        );

        return entity;
    }
}
