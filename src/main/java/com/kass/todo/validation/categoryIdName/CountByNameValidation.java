package com.kass.todo.validation.categoryIdName;

import com.kass.todo.repositories.ICategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CountByNameValidation implements ConstraintValidator<CountByName, String> {
    @Autowired
    private ICategory iCategory;

    @Override
    public void initialize(CountByName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        if(iCategory == null){
            return true;
        }
        return iCategory.countByName(name) < 1;
    }


}
