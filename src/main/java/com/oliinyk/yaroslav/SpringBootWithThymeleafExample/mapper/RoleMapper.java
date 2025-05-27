package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.RoleDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.RoleEntity;

public class RoleMapper {

    private RoleMapper() {}

    public static RoleDto mapToDto(RoleEntity entity) {
        return mapToDto(entity, new RoleDto());
    }

    public static RoleDto mapToDto(RoleEntity entity, RoleDto dto) {
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());

        return dto;
    }

    public static RoleEntity mapToEntity(RoleDto dto) {
        return mapToEntity(dto, new RoleEntity());
    }

    public static RoleEntity mapToEntity(RoleDto dto, RoleEntity entity) {
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());

        return entity;
    }
}
