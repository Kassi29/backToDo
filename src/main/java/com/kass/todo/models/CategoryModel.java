package com.kass.todo.models;

import com.kass.todo.validation.CreateGroup;
import com.kass.todo.validation.categoryName.ExistsByCategoryName;
import com.kass.todo.validation.UpdateGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "category")


public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Put a name for yor category",groups = {CreateGroup.class , UpdateGroup.class})
    @Size(min = 3, message = "The name has to have at least 3 letters",groups = {CreateGroup.class , UpdateGroup.class})
    @Size(max = 20, message = "The category name has to have less than 20 letters",groups = {CreateGroup.class , UpdateGroup.class})
    @ExistsByCategoryName(groups = {CreateGroup.class})

    private String name;

    @NotBlank(message = "Please chose a color")
    private String color;

    public CategoryModel() {
        //for the DB
    }

    public CategoryModel(int id ,String name,  String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
