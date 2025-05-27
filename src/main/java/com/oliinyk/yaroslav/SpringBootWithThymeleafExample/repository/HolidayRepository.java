package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.HolidayEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<HolidayEntity, String> {
}
