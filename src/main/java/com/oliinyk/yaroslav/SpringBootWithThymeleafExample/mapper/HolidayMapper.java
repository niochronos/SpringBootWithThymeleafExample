package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.HolidayDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.HolidayEntity;

public class HolidayMapper {

    private HolidayMapper() {}

    public static HolidayDto mapToDto(HolidayEntity entity) {
        return mapToDto(entity, new HolidayDto());
    }

    public static HolidayDto mapToDto(HolidayEntity entity, HolidayDto dto) {
        dto.setDay(entity.getDay());
        dto.setReason(entity.getReason());
        dto.setType(entity.getType());

        return dto;
    }

    public static HolidayEntity mapToEntity(HolidayDto dto) {
        return mapToEntity(dto, new HolidayEntity());
    }

    public static HolidayEntity mapToEntity(HolidayDto dto, HolidayEntity entity) {
        entity.setDay(dto.getDay());
        entity.setReason(dto.getReason());
        entity.setType(dto.getType());

        return entity;
    }
}
