<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="edu.juanda.tarea6_2.Entities.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 27/09/2023
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Usuario usuario = (Usuario) application.getAttribute("usuario");
%>
<p>Bienvenido: <%= usuario.getNombre() %></p>
</body>
</html>
