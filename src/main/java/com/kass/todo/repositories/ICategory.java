package com.kass.todo.repositories;

import com.kass.todo.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategory extends JpaRepository<CategoryModel, Integer> {
    boolean existsByName(String name);
    boolean existsById(int id);
    @Query("SELECT COUNT(c) FROM CategoryModel c WHERE c.name = :name")
    int countByName(String name);



}
