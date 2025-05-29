package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.RoleDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.RoleEntity;

public class RoleMapper {

    private RoleMapper() {}

    public static RoleDto toDto(RoleEntity entity) {
        return toDto(entity, new RoleDto());
    }

    public static RoleDto toDto(RoleEntity entity, RoleDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());

        return dto;
    }

    public static RoleEntity toEntity(RoleDto dto) {
        return toEntity(dto, new RoleEntity());
    }

    public static RoleEntity toEntity(RoleDto dto, RoleEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());

        return entity;
    }
}
