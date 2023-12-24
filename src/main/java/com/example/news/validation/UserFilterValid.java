package com.example.news.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFilterValid {

    String message() default "При запросе списка пользователей необходимо указать параметры pageSize и pageNumber";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
