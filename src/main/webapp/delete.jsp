<%--
  Created by IntelliJ IDEA.
  User: bookw
  Date: 3/13/2025
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.io.*" %>
<%
    String id = request.getParameter("id");
    if (id != null) {
        request.getRequestDispatcher("Servlet?action=delete&id=" + id).forward(request, response);
    } else {
        response.sendRedirect("index.jsp");
    }
%>

