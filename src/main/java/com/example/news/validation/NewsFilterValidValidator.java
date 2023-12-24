package com.example.news.validation;

import com.example.news.filter.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {

    @Override
    public boolean isValid(NewsFilter value, ConstraintValidatorContext context) {
        if(value.getPageNumber() == null || value.getPageSize() == null){
            return false;
        }
        if(value.getRankId() != null && value.getRankId() < 1
                || value.getUserId() != null && value.getUserId() < 1){
            return false;
        }
        return true;
    }
}
