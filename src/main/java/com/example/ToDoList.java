package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "ToDoListItems")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String task;

    public ToDoList() {}

    public ToDoList(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

}
