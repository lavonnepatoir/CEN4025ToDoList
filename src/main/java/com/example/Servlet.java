package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.util.List;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        viewList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addItem(request);
                break;
            case "delete":
                deleteItem(request);
                break;
            case "view":
                viewList(request, response);
                return;
            default:
                request.setAttribute("error", "Invalid action");
        }

        response.sendRedirect("Servlet");
    }

    private void addItem(HttpServletRequest request) {
        String task = request.getParameter("task");

        if (task != null && !task.trim().isEmpty()) {
            ToDoList toDoItem = new ToDoList(task);
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(toDoItem);
                transaction.commit();
            }
        }
    }

    private void deleteItem(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ToDoList toDoItem = session.get(ToDoList.class, id);

            if (toDoItem != null) {
                session.delete(toDoItem);
            }

            transaction.commit();
        }
    }

    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ToDoList> items = session.createQuery("from ToDoList", ToDoList.class).list();
            request.setAttribute("todoList", items);
            request.getRequestDispatcher("view.jsp").forward(request, response);
        }
    }
}