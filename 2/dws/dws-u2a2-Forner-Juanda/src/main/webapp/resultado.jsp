<%@ page import="edu.juanda.tarea2.entities.Alquiler" %><%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 21/09/2023
  Time: 6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.juanda.tarea2.entities.Alquiler" %>
<html>
<head>
    <title>Muestra del formulario</title>
</head>
<body>

<%

    Alquiler alquiler = new Alquiler(
            request.getParameter("nombre"),
            Integer.parseInt(request.getParameter("dias")),
            request.getParameter("edad"),
            request.getParameter("forma-pago"),
            request.getParameter("extras")
    );
%>

<p>Usted indicó la siguiente información:</p>
<p><strong>Pelicula:</strong> <% out.write(alquiler.getNombre()); %></p>
<p><strong>Dias:</strong> <% out.write(Integer.toString(alquiler.getDias())); %></p>
<p><strong>Edad:</strong> <% out.write(alquiler.getEdad()); %></p>
<p><strong>Forma de pago:</strong> <% out.write(alquiler.getFormaPago()); %></p>
<p><strong>Indicaciones extras:</strong> <% out.write(alquiler.getExtras()); %></p>
</body>
</html>
