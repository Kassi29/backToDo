package com.kass.todo.models;
import jakarta.persistence.*;

@Entity
@Table(name = "status")


public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public StatusModel() {
        //for the DB
    }

    public StatusModel(String name){
        this.name = name;
    }

    public StatusModel(int id,String name) {
        this.id = id;
        this.name = name;
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
}
