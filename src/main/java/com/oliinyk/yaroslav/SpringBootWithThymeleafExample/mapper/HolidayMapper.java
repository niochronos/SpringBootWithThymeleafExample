package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.HolidayDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.HolidayEntity;

public class HolidayMapper {

    private HolidayMapper() {}

    public static HolidayDto toDto(HolidayEntity entity) {
        return toDto(entity, new HolidayDto());
    }

    public static HolidayDto toDto(HolidayEntity entity, HolidayDto dto) {
        if (entity == null) {
            return null;
        }
        dto.setDay(entity.getDay());
        dto.setReason(entity.getReason());
        dto.setType(entity.getType());

        return dto;
    }

    public static HolidayEntity toEntity(HolidayDto dto) {
        return toEntity(dto, new HolidayEntity());
    }

    public static HolidayEntity toEntity(HolidayDto dto, HolidayEntity entity) {
        if (dto == null) {
            return null;
        }
        entity.setDay(dto.getDay());
        entity.setReason(dto.getReason());
        entity.setType(dto.getType());

        return entity;
    }
}
