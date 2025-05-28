package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.impl;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto.HolidayDto;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity.HolidayEntity;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.mapper.HolidayMapper;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.repository.HolidayRepository;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public List<HolidayDto> findAll() {
        Iterable<HolidayEntity> holidays = holidayRepository.findAll();
        return StreamSupport.stream(holidays.spliterator(), false)
            .map(HolidayMapper::mapToDto)
            .toList();
    }
}
