package com.example.news.validation;

import com.example.news.filter.RankFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RankFilterValidValidator implements ConstraintValidator<RankFilterValid, RankFilter> {
    @Override
    public boolean isValid(RankFilter value, ConstraintValidatorContext context) {
        return value.getPageNumber() != null && value.getPageSize() != null;
    }
}
