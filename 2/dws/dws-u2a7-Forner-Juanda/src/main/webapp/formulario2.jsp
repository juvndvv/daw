<%@ page contentType="text
</html;charset=UTF-8" language="java" %>
<jsp:useBean id="alquiler" class="edu.juanda.tarea7.beans.Alquiler"/>
<html>
<head>
    <title>Videoclub online</title>
    <link rel="stylesheet" href="css/main.css" />
    <link rel="stylesheet" href="css/header.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<main>
    <section>
        <p>Usted indicó la siguiente información:</p>
        <p><strong>Película:</strong> <%= alquiler.getNombre() %></p>
        <p><strong>Dias:</strong> <%= alquiler.getDias() %></p>
        <p><strong>Edad:</strong> <%= alquiler.getEdad() %></p>
        <p><strong>Forma de pago:</strong> <%= alquiler.getFormaPago() %></p>
        <p><strong>Indicaciones extras:</strong> <%= alquiler.getExtras() %></p>
    </section>
</main>
</body>
</html>
