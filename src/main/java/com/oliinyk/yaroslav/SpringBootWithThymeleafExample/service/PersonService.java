package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonDto> findAll();

    boolean createNewPerson(PersonDto personDto);

    Optional<PersonDto> findById(int id);

    Optional<PersonDto> readByEmail(String email);

    PersonDto save(PersonDto personDto);


}
