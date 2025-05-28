package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String zipCode;
}
