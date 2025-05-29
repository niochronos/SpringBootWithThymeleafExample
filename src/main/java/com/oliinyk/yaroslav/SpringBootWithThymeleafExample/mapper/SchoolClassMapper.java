package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.SchoolClassDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.SchoolClassEntity;

import java.util.stream.Collectors;

public class SchoolClassMapper {

    private SchoolClassMapper() {}

    public static SchoolClassDto toDto(SchoolClassEntity entity) {
        return toDto(entity, new SchoolClassDto());
    }

    public static SchoolClassDto toDto(SchoolClassEntity entity, SchoolClassDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setClassId(entity.getClassId());
        dto.setName(entity.getName());
        if (entity.getPersons() != null) {
            dto.setPersons(
                entity.getPersons().stream()
                    .map(PersonMapper::toDto)
                    .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static SchoolClassEntity toEntity(SchoolClassDto dto) {
        return toEntity(dto, new SchoolClassEntity());
    }

    public static SchoolClassEntity toEntity(SchoolClassDto dto, SchoolClassEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setClassId(dto.getClassId());
        entity.setName(dto.getName());
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
