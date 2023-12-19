<%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 26/09/2023
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="pedidoAutorizacion" class="edu.juanda.tarea5.beans.PedidoAutorizacionBean" scope="request"/>
<jsp:setProperty name="pedidoAutorizacion" property="prueba" />
<jsp:setProperty name="pedidoAutorizacion" property="cantidad" />
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resolución autorización</title>
</head>
<body>
<main>
    <section class="respuesta">
        <p><strong>Código de la prueba</strong>: <%= pedidoAutorizacion.getPrueba() %></p>
        <p><strong>Cantidad</strong>: <%= pedidoAutorizacion.getCantidad() %></p>
        <p><strong>Estado</strong>: <%= pedidoAutorizacion.getEstado() %></p>
        <p><strong>Motivo</strong>: <%= pedidoAutorizacion.getMotivo() %></p>
    </section>
</main>
</body>
</html>
