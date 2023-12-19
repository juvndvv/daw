package edu.juanda.tarea9.servlets;

import edu.juanda.tarea9.entities.Carrito;
import edu.juanda.tarea9.entities.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {

    @Override
    public void init() {
        // Crea base de datos ficticia para el ejercicio, a nivel de aplicación
        Database db = new Database();
        this.getServletContext().setAttribute("db", db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Crea el carro para la sesión si no existe
        HttpSession session = req.getSession();
        if (session.getAttribute("carrito") == null) {
            Carrito carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        req.getRequestDispatcher("formulario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boton = req.getParameter("btn");     // valor del botón

        if (boton.equals("add")) {
            Carrito carrito = (Carrito) req.getSession().getAttribute("carrito");    // obtiene el carro
            int id = Integer.parseInt(req.getParameter("productos"));                // obtiene el id del form
            carrito.add(id);
            req.getSession().setAttribute("carrito", carrito);

            req.getRequestDispatcher("formulario.jsp").forward(req, resp);

        } else if (boton.equals("end")) {
            req.getRequestDispatcher("finalizar-compra").forward(req, resp);
        }
    }
}
