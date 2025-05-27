package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.CourseDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<CourseDto> findAll(Sort sort);

    Optional<CourseDto> findById(int id);

    void save(CourseDto courseDto);
}
