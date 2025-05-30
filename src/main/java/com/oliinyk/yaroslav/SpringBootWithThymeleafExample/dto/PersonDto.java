package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.annotation.FieldsValueMatch;
import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.annotation.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Data
@Getter
@Setter
//@EqualsAndHashCode
@FieldsValueMatch.List({
    @FieldsValueMatch(
        field = "pwd",
        fieldMatch = "confirmPwd",
        message = "Passwords do not match!"
    ),
    @FieldsValueMatch(
        field = "email",
        fieldMatch = "confirmEmail",
        message = "Email addresses do not match!"
    )
})
public class PersonDto {

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
    @JsonIgnore
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @PasswordValidator
    @JsonIgnore
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
    @JsonIgnore
    private String confirmPwd;

    private RoleDto role;

    private AddressDto address;

    private SchoolClassDto schoolClass;

    private Set<CourseDto> courses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(email, personDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
