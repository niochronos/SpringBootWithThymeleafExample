package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.HolidayDto;

import java.util.List;

public interface HolidayService {

    List<HolidayDto> findAll();
}
