package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.PersonEntity;

import java.util.stream.Collectors;

public class PersonMapper {

    private PersonMapper() {}

    public static PersonDto mapToDto(PersonEntity entity) {
        return PersonMapper.mapToDto(entity, new PersonDto());
    }

    public static PersonDto mapToDto(PersonEntity entity, PersonDto dto) {
        dto.setPersonId(entity.getPersonId());
        dto.setName(entity.getName());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setEmail(entity.getEmail());
        dto.setPwd(entity.getPwd());
        dto.setRole(
            RoleMapper.mapToDto(entity.getRole())
        );
        dto.setAddress(
            AddressMapper.mapToDto(entity.getAddress())
        );
        dto.setSchoolClass(
            SchoolClassMapper.mapToDto(entity.getSchoolClassEntity())
        );
        dto.setCourses(entity.getCourses().stream()
            .map(CourseMapper::mapToDto)
            .collect(Collectors.toSet())
        );

        return dto;
    }

    public static PersonEntity mapToEntity(PersonDto dto) {
        return PersonMapper.mapToEntity(dto, new PersonEntity());
    }

    public static PersonEntity mapToEntity(PersonDto dto, PersonEntity entity) {
        entity.setPersonId(dto.getPersonId());
        entity.setName(dto.getName());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setEmail(dto.getEmail());
        entity.setPwd(dto.getPwd());
        entity.setRole(
            RoleMapper.mapToEntity(dto.getRole())
        );
        entity.setAddress(
            AddressMapper.mapToEntity(dto.getAddress())
        );
        entity.setSchoolClassEntity(
            SchoolClassMapper.mapToEntity(dto.getSchoolClass())
        );
        entity.setCourses(
            dto.getCourses().stream()
                .map(CourseMapper::mapToEntity)
                .collect(Collectors.toSet())
        );

        return entity;
    }
}
