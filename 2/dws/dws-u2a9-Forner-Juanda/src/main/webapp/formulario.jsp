<%@ page import="edu.juanda.tarea9.entities.Carrito" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="edu.juanda.tarea9.entities.Database" %><%--
  Created by IntelliJ IDEA.
  User: jdani
  Date: 21/09/2023
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario</title>
</head>
<body>
    <main>
        <section id="compra">
            <form action="carrito" method="post">
                <label for="productos">Productos</label>
                <select id="productos" name="productos">
                    <option value="1">Air Force 1</option>
                    <option value="2">Adidas Superstar</option>
                    <option value="3">Neke Delmer</option>
                    <option value="4">Chanclas</option>
                    <option value="5">Tacones</option>
                </select>
                <button name="btn" type="submit" value="add">Añadir</button>
                <button name="btn" type="submit" value="end">Finalizar compra</button>
            </form>
        </section>

        <section id="carrito">
            <%
                Database db = (Database) application.getAttribute("db");
                Carrito carrito = (Carrito) session.getAttribute("carrito");

                if (carrito != null) {
                    Enumeration enumeration = carrito.getCarrito().keys();
                    while (enumeration.hasMoreElements()) {
                        int id = (int) enumeration.nextElement();
                        int cantidad = carrito.getCarrito().get(id);

                        System.out.println(db.getNombre(id));
                        out.write(String.format("<p>%s x%d</p>", db.getNombre(id), cantidad));
                    }

                }
            %>
        </section>
    </main>
</body>
</html>
