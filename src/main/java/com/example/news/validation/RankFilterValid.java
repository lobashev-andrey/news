package com.example.news.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RankFilterValidValidator.class)
public @interface RankFilterValid {

    String message() default "При запросе списка категорий новостей необходимо указать параметры pageSize и pageNumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
