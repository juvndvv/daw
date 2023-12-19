package edu.juanda.tarea6_2;

import edu.juanda.tarea6_2.Entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>(Arrays.asList(
                new Usuario("Juanda", "1234"),
                new Usuario("Jorge", "12345"),
                new Usuario("Rosa", "12345")
        ));

        Usuario usuarioForm = new Usuario(req.getParameter("usuario"), req.getParameter("password"));

        Usuario currUsuario;
        try {
            currUsuario = usuarios.stream()
                .filter(usuario -> usuario.equals(usuarioForm))
                .collect(Collectors.toList())
                .get(0);

        } catch (IndexOutOfBoundsException e) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            currUsuario = new Usuario();
        }

        this.getServletContext().setAttribute("usuario", currUsuario);       // Application scope
        resp.sendRedirect("bienvenida");
    }
}

