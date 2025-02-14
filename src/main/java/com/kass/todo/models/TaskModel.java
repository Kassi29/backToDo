package com.kass.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "task")


public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Put a name for yor task")
    @Size(min = 3, message = "The name has to have at least 3 letters")
    @Size(max = 10, message = "The name has to have max 10 letters")
    private String name;
    @NotBlank(message = "Put a description for yor task")
    @Size(min = 3, message = "The description has to have at least 3 letters")
    @Size(max = 20, message = "The description has to have max 20 letters")
    private String description;

    @NotNull(message = "Please choose a category")
    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(name = "statusID")
    private StatusModel status;

    public TaskModel() {
        //for the DB
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }




}
