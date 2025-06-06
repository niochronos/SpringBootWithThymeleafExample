package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    private String pwd;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RoleEntity.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private RoleEntity role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private SchoolClassEntity schoolClass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "person_course",
        joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "personId")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "courseId")
        }
    )
    private Set<CourseEntity> courses = new HashSet<>();
}
