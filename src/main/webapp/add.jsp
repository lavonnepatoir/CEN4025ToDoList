<%--
  Created by IntelliJ IDEA.
  User: bookw
  Date: 3/13/2025
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add task</title>
</head>
<body>
<form action="Servlet?action=add" method="post">
    <label for="task">Enter task:</label>
    <input type="text" id="task" name="task" required>
    <button type="submit">Add task</button>
</form>
<a href="index.jsp">Return to list</a>
</body>
</html>


