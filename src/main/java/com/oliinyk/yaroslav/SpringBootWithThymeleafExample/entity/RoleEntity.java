package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int roleId;

    private String roleName;
}
