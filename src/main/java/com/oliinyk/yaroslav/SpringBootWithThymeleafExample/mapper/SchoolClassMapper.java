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
        SchoolClassMapper.toDtoShallow(entity, dto);
        if (entity.getPersons() != null && !entity.getPersons().isEmpty()) {
            dto.setPersons(
                entity.getPersons().stream()
                    .map(PersonMapper::toDtoShallow)
                    .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static SchoolClassDto toDtoShallow(SchoolClassEntity entity) {
        return toDtoShallow(entity, new SchoolClassDto());
    }

    public static SchoolClassDto toDtoShallow(SchoolClassEntity entity, SchoolClassDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setClassId(entity.getClassId());
        dto.setName(entity.getName());

        return dto;
    }

    public static SchoolClassEntity toEntity(SchoolClassDto dto) {
        return toEntity(dto, new SchoolClassEntity());
    }

    public static SchoolClassEntity toEntity(SchoolClassDto dto, SchoolClassEntity entity) {
        if (dto == null) {
            return null;
        }
        SchoolClassMapper.toEntityShallow(dto, entity);
        if (dto.getPersons() != null && !dto.getPersons().isEmpty()) {
            entity.setPersons(
                dto.getPersons().stream()
                    .map(PersonMapper::toEntityShallow)
                    .collect(Collectors.toSet())
            );
        }

        return entity;
    }

    public static SchoolClassEntity toEntityShallow(SchoolClassDto dto) {
        return toEntityShallow(dto, new SchoolClassEntity());
    }

    public static SchoolClassEntity toEntityShallow(SchoolClassDto dto, SchoolClassEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setClassId(dto.getClassId());
        entity.setName(dto.getName());

        return entity;
    }
}
