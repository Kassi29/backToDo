package com.kass.todo.validation.categoryName;

import com.kass.todo.repositories.ICategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByNameCatValidation implements ConstraintValidator <ExistsByCategoryName ,String>{

    @Autowired
    private ICategory iCategory;


    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        if(iCategory == null){
            return true;
        }
        return !iCategory.existsByName(name);

    }
}
