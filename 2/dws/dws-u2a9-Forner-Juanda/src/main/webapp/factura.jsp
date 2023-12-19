<%@ page import="edu.juanda.tarea9.entities.Database" %>
<%@ page import="edu.juanda.tarea9.entities.Carrito" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Factura</title>
</head>
<body>
    <%
        Database db = (Database) application.getAttribute("db");
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        // Muestra información sobre los productos comprados y al final el total
        float total = 0;
        Enumeration<Integer> enumeration = carrito.getCarrito().keys();
        while (enumeration.hasMoreElements()) {
            int id = (int) enumeration.nextElement();

            String nombre = db.getNombre(id);
            int cantidad = carrito.getCarrito().get(id);

            float precio = db.getPrecio(id);
            float totalProducto = cantidad * precio;
            total += totalProducto;

            out.write(String.format("<p><strong>%s</strong> x%dud %.2f€</p>", nombre, cantidad, totalProducto));
        }

        out.write(String.format("<p><strong>Total:</strong> %.2f€", total));

    %>
</body>
</html>
