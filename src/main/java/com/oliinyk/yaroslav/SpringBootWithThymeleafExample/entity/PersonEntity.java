package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "person")
public class PersonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    private String name;

    private String mobileNumber;

    private String email;

//    Not needed on backend
//    @Transient
//    private String confirmEmail;

    private String pwd;

//    Not needed on backend
//    @Transient
//    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = RoleEntity.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private RoleEntity role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private SchoolClassEntity schoolClassEntity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "person_courses",
        joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "personId")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "courseId")
        }
    )
    private Set<CourseEntity> courses = new HashSet<>();
}
