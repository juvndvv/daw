package edu.juanda.tarea7.servlets;

import edu.juanda.tarea7.beans.Alquiler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/alquilar-pelicula")
public class CapturaDatosVideoclub extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario1.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Creamos el Bean
        Alquiler alquiler = new Alquiler();

        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String dias = request.getParameter("dias");
        String edad = request.getParameter("edad");
        String formaPago = request.getParameter("formaPago");
        String extras = request.getParameter("extras");

        // Establecer los valores en el objeto Alquiler
        alquiler.setNombre(nombre);
        alquiler.setDias(Integer.parseInt(dias));
        alquiler.setEdad(edad);
        alquiler.setFormaPago(formaPago);
        alquiler.setExtras(extras);

        request.setAttribute("alquiler", alquiler);

        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario2.jsp");
        dispatcher.forward(request, response);
    }
}
