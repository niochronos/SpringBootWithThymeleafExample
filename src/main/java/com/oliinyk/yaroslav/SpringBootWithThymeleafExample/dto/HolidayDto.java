package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.type.HolidayType;
import lombok.Data;

@Data
public class HolidayDto {

    private String day;

    private String reason;

    private HolidayType type;
}
