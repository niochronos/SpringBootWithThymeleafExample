package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.type.HolidayType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "holidays")
public class HolidayEntity extends BaseEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private HolidayType type;
}
