package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    PersonEntity readByEmail(String email);
}
