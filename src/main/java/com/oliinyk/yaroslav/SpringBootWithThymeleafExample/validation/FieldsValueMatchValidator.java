package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.validation;

import com.oliinyk.yaroslav.SpringBootWithThymeleafExample.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        if (fieldValue == null) {
            return fieldMatchValue == null;
        }
        if(fieldValue.toString().startsWith("$2a")) {
            return true;
        }
        return fieldValue.equals(fieldMatchValue);
    }
}
