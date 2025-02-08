package com.kass.todo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "task")

@Getter
@Setter
@NoArgsConstructor

public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(name = "statusID")
    private StatusModel status;

}
