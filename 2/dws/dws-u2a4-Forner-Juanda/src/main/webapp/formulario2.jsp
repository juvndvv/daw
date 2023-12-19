<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos del formulario</title>
</head>
<body>
    <main>
        <section>
            <p><strong>Productos</strong>:
                <%
                    String[] productos = request.getParameterValues("productos");

                    if (productos != null) {
                        for (String producto : productos) {
                            out.write(String.format("%s ", producto));
                        }
                    }
                %></p>
            <p><strong>Forma de pago</strong>: <%= request.getParameter("forma-pago")%> </p>
            <p><strong>Envio</strong>: <%= request.getParameterValues("envio")[0]%></p>
            <p><strong>Caracteristicas especiales</strong>: <%= request.getParameter("caracteristicas")%></p>
            <p><strong>Nombre</strong>: <%= request.getParameter("nombre")%></p>
            <p><strong>Apellidos</strong>: <%= request.getParameter("apellidos")%></p>
            <p><strong>Documento nacional de identidad:</strong>: <%= request.getParameter("dni")%></p>
            <p><strong>Telefono</strong>: <%= request.getParameter("telefono")%></p>
        </section>
    </main>
</body>
</html>