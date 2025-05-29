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
        dto.setSchoolClass(
            SchoolClassMapper.toDto(entity.getSchoolClass())
        );
        if (entity.getCourses() != null) {
            dto.setCourses(entity.getCourses().stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public static PersonEntity toEntity(PersonDto dto) {
        return PersonMapper.toEntity(dto, new PersonEntity());
    }

    public static PersonEntity toEntity(PersonDto dto, PersonEntity entity) {
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
        entity.setSchoolClass(
            SchoolClassMapper.toEntity(dto.getSchoolClass())
        );
        if (dto.getCourses() != null) {
            entity.setCourses(
                dto.getCourses().stream()
                    .map(CourseMapper::toEntity)
                    .collect(Collectors.toSet())
            );
        }

        return entity;
    }
}
