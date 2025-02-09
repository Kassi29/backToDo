package com.kass.todo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByNameCatValidation .class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByCategoryName {
    String message() default "A category with this name already exists. Please choose another name." ;

    Class<?> [] groups() default {};
    Class<? extends Payload > [] payload() default {};


}
