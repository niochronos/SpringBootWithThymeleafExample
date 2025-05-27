package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.impl;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.SchoolClassDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.SchoolClassMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.SchoolClassRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public List<SchoolClassDto> findAll() {
        return schoolClassRepository.findAll().stream()
            .map(SchoolClassMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public SchoolClassDto save(SchoolClassDto schoolClassDto) {
        return SchoolClassMapper.mapToDto(
            schoolClassRepository.save(SchoolClassMapper.mapToEntity(schoolClassDto))
        );
    }

    @Override
    public Optional<SchoolClassDto> findById(int id) {
        return schoolClassRepository.findById(id).map(SchoolClassMapper::mapToDto);
    }

    @Override
    public void deleteById(int id) {
        schoolClassRepository.deleteById(id);
    }
}
