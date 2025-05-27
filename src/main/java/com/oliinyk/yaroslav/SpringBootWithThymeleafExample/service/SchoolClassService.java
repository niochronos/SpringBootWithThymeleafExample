package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.SchoolClassDto;

import java.util.List;
import java.util.Optional;

public interface SchoolClassService {

    List<SchoolClassDto> findAll();

    SchoolClassDto save(SchoolClassDto schoolClassDto);

    Optional<SchoolClassDto> findById(int id);

    void deleteById(int id);
}
