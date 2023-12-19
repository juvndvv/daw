package edu.juanda.tarea9.servlets;

import edu.juanda.tarea9.entities.Carrito;
import edu.juanda.tarea9.entities.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/finalizar-compra")
public class FinalizarCompraServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database db = (Database) this.getServletContext().getAttribute("db");
        Carrito carrito = (Carrito) req.getSession().getAttribute("carrito");

        float total = 0;
        Enumeration enumeration = carrito.getCarrito().keys();
        while (enumeration.hasMoreElements()) {
            int id = (int) enumeration.nextElement();
            int cantidad = carrito.getCarrito().get(id);

            total += db.getPrecio(id) * cantidad;
        }

        req.setAttribute("total", total);
        req.getRequestDispatcher("factura.jsp").forward(req, resp);
    }
}
