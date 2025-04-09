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
        this.task = scrub(task);
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    private String scrub(String input) {
        if (input == null) {return null;}

        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
