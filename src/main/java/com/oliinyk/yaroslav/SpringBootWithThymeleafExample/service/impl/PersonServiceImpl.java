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

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(PersonDto personDto) {
        RoleEntity roleEntity = roleRepository.getByRoleName(AppConstants.STUDENT_ROLE);
        personDto.setRole(RoleMapper.mapToDto(roleEntity));

//        String encodedPwd = passwordEncoder.encode(personDto.getPwd());
//        personDto.setPwd(encodedPwd);
//        personDto.setConfirmPwd(encodedPwd);

        PersonEntity personEntity = personRepository.save(
            PersonMapper.mapToEntity(personDto)
        );

        return personEntity.getPersonId() > 0;
    }

    @Override
    public Optional<PersonDto> findById(int id) {
        return personRepository.findById(id).map(PersonMapper::mapToDto);
    }

    @Override
    public Optional<PersonDto> readByEmail(String email) {
        return personRepository.readByEmail(email).map(PersonMapper::mapToDto);
    }

    @Override
    public void save(PersonDto personDto) {
        personRepository.save(PersonMapper.mapToEntity(personDto));
    }
}
