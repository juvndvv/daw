<%@ page import="edu.juanda.tarea8.Entities.Alumno" %><%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 27/09/2023
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alumno" class="edu.juanda.tarea8.Entities.Alumno" scope="request" />
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Datos alumno</title>
</head>
<body>
<p><strong>Nombre</strong>: <jsp:getProperty name="alumno" property="nombre"/></p>
<p><strong>Primer apellido</strong>: <jsp:getProperty name="alumno" property="primerApellido"/></p>
<p><strong>Segundo apellido</strong>: <jsp:getProperty name="alumno" property="segundoApellido"/></p>
<p><strong>DNI</strong>: <jsp:getProperty name="alumno" property="dni"/></p>
</body>
</html>
