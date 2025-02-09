package com.kass.todo.services;
import com.kass.todo.exceptions.NotFoundException;
import com.kass.todo.models.CategoryModel;
import com.kass.todo.repositories.ICategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final ICategory iCategory;

    public CategoryService(ICategory iCategory) {
        this.iCategory = iCategory;
    }

    @Transactional(readOnly = true)
    public CategoryModel getCategoryById(int id) {
        return iCategory.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

}
