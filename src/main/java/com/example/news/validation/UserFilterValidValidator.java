package com.example.news.validation;

import com.example.news.filter.UserFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserFilterValidValidator implements ConstraintValidator<UserFilterValid, UserFilter> {

    @Override
    public boolean isValid(UserFilter value, ConstraintValidatorContext context) {
        return value.getPageNumber() != null && value.getPageSize() != null;
    }
}
