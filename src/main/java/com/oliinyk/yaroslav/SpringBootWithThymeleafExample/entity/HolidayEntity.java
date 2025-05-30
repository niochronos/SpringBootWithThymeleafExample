package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.type.HolidayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "holiday")
public class HolidayEntity extends BaseEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private HolidayType type;
}
