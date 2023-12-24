package com.example.news.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NewsFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsFilterValid {

    String message() default "При запросе списка категорий новостей необходимо указать параметры pageSize и pageNumber." +
            " Значения rankId и userId должны быть целыми числами больше нуля";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
