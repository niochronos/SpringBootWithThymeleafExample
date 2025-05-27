package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.AddressDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.AddressEntity;

public class AddressMapper {

    private AddressMapper() {}

    public static AddressDto mapToDto(AddressEntity entity) {
        return AddressMapper.mapToDto(entity, new AddressDto());
    }

    public static AddressDto mapToDto(AddressEntity entity, AddressDto dto) {
        dto.setAddressId(entity.getAddressId());
        dto.setAddress1(entity.getAddress1());
        dto.setAddress2(entity.getAddress2());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());

        return dto;
    }

    public static AddressEntity mapToEntity(AddressDto dto) {
        return AddressMapper.mapToEntity(dto, new AddressEntity());
    }

    public static AddressEntity mapToEntity(AddressDto dto, AddressEntity entity) {
        entity.setAddressId(dto.getAddressId());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());

        return entity;
    }
}
