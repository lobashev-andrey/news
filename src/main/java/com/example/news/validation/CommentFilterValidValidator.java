package com.example.news.validation;

import com.example.news.filter.CommentFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CommentFilterValidValidator implements ConstraintValidator<CommentFilterValid, CommentFilter> {
    @Override
    public boolean isValid(CommentFilter value, ConstraintValidatorContext context) {
        return value.getNewsId() != null && value.getNewsId() > 0;
    }
}
