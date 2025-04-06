package com.example.console;

import com.example.HibernateUtil;
import com.example.ToDoList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean listOptions = true;

        while (listOptions) {
            System.out.println("\n1. Add an item  2. Delete an item  3. View entire to-do list  4. Stop");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    deleteItem(scanner);
                    break;
                case 3:
                    viewList();
                    break;
                case 4:
                    listOptions = false;
                    break;
                default:
                    System.out.println("Error, invalid option, try again");
            }
        }
        scanner.close();
        HibernateUtil.shutdown();
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter a to-do item to add: ");
        String task = scanner.nextLine();

        ToDoList toDoItem = new ToDoList(task);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(toDoItem);

        transaction.commit();
        session.close();
        System.out.println("The following item has been added: " + task);
    }

    private static void deleteItem(Scanner scanner) {
        viewList();
        System.out.print("Enter the List # of the item needing deletion: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        ToDoList toDoItem = session.get(ToDoList.class, id);
        if (toDoItem != null) {
            session.delete(toDoItem);
            System.out.println("The following item has been deleted: " + toDoItem.getTask());
        } else {
            System.out.println("Error, item not found");
        }

        transaction.commit();
        session.close();
    }

    private static void viewList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ToDoList> items = session.createQuery("from ToDoList", ToDoList.class).list();
        session.close();

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
