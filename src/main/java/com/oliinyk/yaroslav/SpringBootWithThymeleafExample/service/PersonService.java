package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;

import java.util.Optional;

public interface PersonService {

    boolean createNewPerson(PersonDto personDto);

    Optional<PersonDto> findById(int id);

    Optional<PersonDto> readByEmail(String email);

    void save(PersonDto personDto);
}
