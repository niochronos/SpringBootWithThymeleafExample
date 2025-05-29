package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.AddressDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.AddressEntity;

public class AddressMapper {

    private AddressMapper() {}

    public static AddressDto toDto(AddressEntity entity) {
        return AddressMapper.toDto(entity, new AddressDto());
    }

    public static AddressDto toDto(AddressEntity entity, AddressDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setAddressId(entity.getAddressId());
        dto.setAddress1(entity.getAddress1());
        dto.setAddress2(entity.getAddress2());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());

        return dto;
    }

    public static AddressEntity toEntity(AddressDto dto) {
        return AddressMapper.toEntity(dto, new AddressEntity());
    }

    public static AddressEntity toEntity(AddressDto dto, AddressEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setAddressId(dto.getAddressId());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());

        return entity;
    }
}
