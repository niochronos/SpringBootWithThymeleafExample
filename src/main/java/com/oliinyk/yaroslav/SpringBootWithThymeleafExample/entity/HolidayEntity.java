package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.type.HolidayType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "holiday")
public class HolidayEntity extends BaseEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private HolidayType type;
}
