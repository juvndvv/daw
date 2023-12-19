package edu.juanda.tarea6_2;

import edu.juanda.tarea6_2.Entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bienvenida")
public class BienvenidaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Comprobar si un usuario ha iniciado sesion
        if ((Usuario) this.getServletContext().getAttribute("usuario") == null) {
            resp.sendRedirect("login");
        } else {
            req.getRequestDispatcher("bienvenida.jsp").forward(req, resp);
        }

    }
}
