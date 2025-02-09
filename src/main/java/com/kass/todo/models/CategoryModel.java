package com.kass.todo.models;

import jakarta.persistence.*;


@Entity
@Table(name = "category")


public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

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
