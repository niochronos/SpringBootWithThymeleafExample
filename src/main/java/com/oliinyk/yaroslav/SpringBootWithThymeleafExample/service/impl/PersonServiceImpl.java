package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.impl;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.constant.AppConstants;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.PersonDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.PersonEntity;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.RoleEntity;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.PersonMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.RoleMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.PersonRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.RoleRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
            .map(PersonMapper::toDto)
            .toList();
    }

    public boolean createNewPerson(PersonDto personDto) {
        RoleEntity roleEntity = roleRepository.getByRoleName(AppConstants.STUDENT_ROLE);
        personDto.setRole(RoleMapper.toDto(roleEntity));

        String encodedPwd = passwordEncoder.encode(personDto.getPwd());
        personDto.setPwd(encodedPwd);
        personDto.setConfirmPwd(encodedPwd);

        PersonEntity personEntity = personRepository.save(
            PersonMapper.toEntity(personDto)
        );

        return personEntity.getPersonId() > 0;
    }

    @Override
    public Optional<PersonDto> findById(int id) {
        return personRepository.findById(id).map(PersonMapper::toDto);
    }

    @Override
    public Optional<PersonDto> readByEmail(String email) {
        return personRepository.readByEmail(email).map(PersonMapper::toDto);
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        return PersonMapper.toDto(
            personRepository.save(PersonMapper.toEntity(personDto))
        );
    }
}
