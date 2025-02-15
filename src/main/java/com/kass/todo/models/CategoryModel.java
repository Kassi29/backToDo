package com.kass.todo.models;

import com.kass.todo.validation.ExistsByCategoryName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "category")


public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Put a name for yor category")
    @Size(min = 3, message = "The name has to have at least 3 letters")
    @Size(max = 20, message = "The category name has to have less than 20 letters")
    @ExistsByCategoryName(message = "A category with this name already exists. Please choose another name.")
    private String name;

    @NotBlank
    private String color;

    public CategoryModel() {
        //for the DB
    }

    public CategoryModel(int id ,String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
