package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "person")
public class PersonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient
    @JsonIgnore
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
//    @PasswordValidator
    @JsonIgnore
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
    @Transient
    @JsonIgnore
    private String confirmPwd;

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
