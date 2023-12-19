<%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 27/09/2023
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comprobar DNI</title>
</head>
<body>
<h2>Introduce DNI a buscar</h2>
<form action="comprobarAlumno" method="post">
    <input type="text" name="dni" id="dni">
    <button type="submit">Enviar</button>
</form>
</body>
</html>
