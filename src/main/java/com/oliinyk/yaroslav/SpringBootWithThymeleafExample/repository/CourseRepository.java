package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    /*
    Spring Data JPA allows us to apply static sorting by adding the OrderBy keyword
    to the method name along with the property name and sort direction (Asc or Desc).
    * */
    List<CourseEntity> findByOrderByNameDesc();

    /*
    The Asc keyword is optional as OrderBy, by default,
    sorts the results in the ascending order.
    * */
    List<CourseEntity> findByOrderByName();
}
