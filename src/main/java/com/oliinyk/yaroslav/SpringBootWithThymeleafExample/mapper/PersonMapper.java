package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.PersonEntity;

import java.util.stream.Collectors;

public class PersonMapper {

    private PersonMapper() {}

    public static PersonDto toDto(PersonEntity entity) {
        return PersonMapper.toDto(entity, new PersonDto());
    }

    public static PersonDto toDto(PersonEntity entity, PersonDto dto) {
        if (entity == null) {
            return null;
        }
        PersonMapper.toDtoShallow(entity, dto);
        dto.setSchoolClass(
            SchoolClassMapper.toDtoShallow(entity.getSchoolClass())
        );
        if (entity.getCourses() != null && !entity.getCourses().isEmpty()) {
            dto.setCourses(entity.getCourses().stream()
                .map(CourseMapper::toDtoShallow)
                .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static PersonDto toDtoShallow(PersonEntity entity) {
        return PersonMapper.toDtoShallow(entity, new PersonDto());
    }

    public static PersonDto toDtoShallow(PersonEntity entity, PersonDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setPersonId(entity.getPersonId());
        dto.setName(entity.getName());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setEmail(entity.getEmail());
        dto.setPwd(entity.getPwd());
        dto.setRole(
            RoleMapper.toDto(entity.getRole())
        );
        dto.setAddress(
            AddressMapper.toDto(entity.getAddress())
        );

        return dto;
    }

    public static PersonEntity toEntity(PersonDto dto) {
        return PersonMapper.toEntity(dto, new PersonEntity());
    }

    public static PersonEntity toEntity(PersonDto dto, PersonEntity entity) {
        if (dto == null) {
            return null;
        }
        PersonMapper.toEntityShallow(dto, entity);
        entity.setSchoolClass(
            SchoolClassMapper.toEntityShallow(dto.getSchoolClass())
        );
        if (dto.getCourses() != null && !dto.getCourses().isEmpty()) {
            entity.setCourses(
                dto.getCourses().stream()
                    .map(CourseMapper::toEntityShallow)
                    .collect(Collectors.toSet())
            );
        }

        return entity;
    }

    public static PersonEntity toEntityShallow(PersonDto dto) {
        return PersonMapper.toEntityShallow(dto, new PersonEntity());
    }

    public static PersonEntity toEntityShallow(PersonDto dto, PersonEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setPersonId(dto.getPersonId());
        entity.setName(dto.getName());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setEmail(dto.getEmail());
        entity.setPwd(dto.getPwd());
        entity.setRole(
            RoleMapper.toEntity(dto.getRole())
        );
        entity.setAddress(
            AddressMapper.toEntity(dto.getAddress())
        );

        return entity;
    }
}
