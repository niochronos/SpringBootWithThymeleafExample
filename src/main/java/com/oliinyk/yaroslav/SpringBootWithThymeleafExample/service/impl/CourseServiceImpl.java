package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.impl;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.CourseDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.CourseMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.CourseRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDto> findAll(Sort sort) {
        return courseRepository.findAll(sort).stream()
            .map(CourseMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> findById(int id) {
        return courseRepository.findById(id).map(CourseMapper::mapToDto);
    }

    @Override
    public void save(CourseDto courseDto) {
        courseRepository.save(CourseMapper.mapToEntity(courseDto));
    }
}
