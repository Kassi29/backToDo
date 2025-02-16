package com.kass.todo.controllers;

import com.kass.todo.exceptions.ForeignKeyConstraintViolationException;
import com.kass.todo.models.CategoryModel;
import com.kass.todo.services.CategoryService;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryModel> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable int id){
        CategoryModel category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryModel category , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return getValidationErrors(bindingResult);
        }
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody CategoryModel categoryModel, BindingResult bindingResult, @PathVariable int id){
        if(bindingResult.hasErrors()){
           return getValidationErrors(bindingResult);
        }
        return new ResponseEntity<>(categoryService.updateCategory(id,categoryModel),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        try{
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ForeignKeyConstraintViolationException("You cannot delete this category because it has tasks associated with it. Please delete the tasks first.");
        }
    }


    private ResponseEntity<Object> getValidationErrors(BindingResult bindingResult){
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(errorMessages,HttpStatus.BAD_REQUEST);
    }

}
