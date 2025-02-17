package com.kass.todo.services;
import com.kass.todo.exceptions.ExistsNameCategoryException;
import com.kass.todo.exceptions.NotFoundException;
import com.kass.todo.models.CategoryModel;
import com.kass.todo.repositories.ICategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final ICategory iCategory;

    public CategoryService(ICategory iCategory) {
        this.iCategory = iCategory;
    }

    @Transactional(readOnly = true)
    public List<CategoryModel> getAllCategories() {
        return iCategory.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryModel getCategoryById(int id) {
        return iCategory.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Transactional
    public CategoryModel createCategory (CategoryModel categoryModel){ return iCategory.save(categoryModel);}

    @Transactional
    public CategoryModel updateCategory(int id, CategoryModel categoryModel) {
        CategoryModel existingCategory = getCategoryById(id);

        if(!Objects.equals(existingCategory.getName(), categoryModel.getName())) {
            if (iCategory.countByName(categoryModel.getName()) > 0) {
                throw new ExistsNameCategoryException("A category with this name already exists. Please choose another name.");
            }
            existingCategory.setName(categoryModel.getName());
        }
        existingCategory.setColor(categoryModel.getColor());

        existingCategory.setColor(categoryModel.getColor());
        return iCategory.save(existingCategory);
    }

    @Transactional
    public void deleteCategory(int id){
        CategoryModel existingCategory = getCategoryById(id);
        iCategory.delete(existingCategory);
    }

    public boolean existCategory(int id){
        return iCategory.existsById(id);
    }


}
