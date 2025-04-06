<%@ page import="java.util.List, com.example.ToDoList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h2>To-Do List</h2>
<ul>
    <% List<ToDoList> todoList = (List<ToDoList>) request.getAttribute("todoList");
        if (todoList != null) {
            for (ToDoList item : todoList) { %>
    <li>
        <%= item.getTask() %>
        <form action="Servlet" method="post" style="display:inline;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%= item.getId() %>">
            <button type="submit">Delete</button>
        </form>
    </li>
    <%     }
    } else { %>
    <p>Add tasks to get started!</p>
    <% } %>
</ul>

<h3>Add a Task</h3>
<form action="Servlet" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="task" placeholder="Enter a task" required>
    <button type="submit">Add</button>
</form>
</body>
</html>
