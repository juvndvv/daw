<%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 26/09/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/solicitud.css">
    <title>Solicitud de autorización</title>
</head>

<body>
<main>
    <form action="respuestaAutorizacion.jsp">
        <h3>Solicitud de autorización</h3>
        <section class="entradas">
            <section class="entrada">
                <label for="prueba">Código prueba</label>
                <input type="text" name="prueba" id="prueba">
            </section>

            <section class="entrada">
                <label for="cantidad">Cantidad</label>
                <input type="number" name="cantidad" id="cantidad" min="1">
            </section>
            <section class="submit-section">
                <button type="submit">Enviar</button>
            </section>
        </section>
    </form>
</main>
</body>

</html>
