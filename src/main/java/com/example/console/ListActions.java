package com.example.console;

import com.example.HibernateUtil;
import com.example.ToDoList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ListActions {

    public static void addItem(String task) {
        ToDoList toDoItem = new ToDoList(task);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(toDoItem);
            transaction.commit();
        }
        System.out.println("Added: " + task);
    }

    public static void deleteItem(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ToDoList toDoItem = session.get(ToDoList.class, id);
            if (toDoItem != null) {
                session.delete(toDoItem);
                System.out.println("Deleted: " + toDoItem.getTask());
            } else {
                System.out.println("Error: Item not found");
            }
            transaction.commit();
        }
    }

    public static void viewList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ToDoList> items = session.createQuery("from ToDoList", ToDoList.class).list();
            if (items.isEmpty()) {
                System.out.println("The to-do list is currently empty.");
            } else {
                System.out.println("To-Do List:");
                for (ToDoList item : items) {
                    System.out.println(item);
                }
            }
        }
    }
}
