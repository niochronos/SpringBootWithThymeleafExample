package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.ContactDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.ContactEntity;

public class ContactMapper {

    private ContactMapper() {}

    public static ContactDto mapToDto(ContactEntity entity) {
        return ContactMapper.mapToDto(entity, new ContactDto());
    }

    public static ContactDto mapToDto(ContactEntity entity, ContactDto dto) {
        dto.setContactId(entity.getContactId());
        dto.setName(entity.getName());
        dto.setMobileNum(entity.getMobileNum());
        dto.setEmail(entity.getEmail());
        dto.setSubject(entity.getSubject());
        dto.setMessage(entity.getMessage());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static ContactEntity mapToEntity(ContactDto dto) {
        return ContactMapper.mapToEntity(dto, new ContactEntity());
    }

    public static ContactEntity mapToEntity(ContactDto dto, ContactEntity entity) {
        entity.setContactId(dto.getContactId());
        entity.setName(dto.getName());
        entity.setMobileNum(dto.getMobileNum());
        entity.setEmail(dto.getEmail());
        entity.setSubject(dto.getSubject());
        entity.setMessage(dto.getMessage());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
