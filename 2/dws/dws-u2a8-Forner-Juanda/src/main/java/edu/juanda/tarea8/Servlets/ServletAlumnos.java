package edu.juanda.tarea8.Servlets;

import edu.juanda.tarea8.Entities.Alumno;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet("/comprobarAlumno")
public class ServletAlumnos extends HttpServlet {
    private ArrayList<Alumno> alumnos;

    public void init() {
        alumnos  = new ArrayList<>();

        Alumno alu1 = new Alumno("Juan Daniel", "Forner", "Garriga", "46077483L");
        Alumno alu2 = new Alumno("Jorge Gabriel", "Forner", "Garriga", "46077484C");
        Alumno alu3 = new Alumno("Rosa Maria", "Garriga", "Hidalgo", "123456789A");

        alumnos.add(alu1);
        alumnos.add(alu2);
        alumnos.add(alu3);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("comprobarDni.jsp").forward(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Alumno alumno = alumnos.stream()
                    .filter(al -> al.getDni().equalsIgnoreCase(req.getParameter("dni")))
                    .collect(Collectors.toList()).get(0);

            req.setAttribute("alumno", alumno);
            req.getRequestDispatcher("muestraDatosAlumno.jsp").forward(req, resp);

        } catch (IndexOutOfBoundsException e) {
            req.getRequestDispatcher("comprobarDni.jsp").forward(req, resp);
        }
    }
}
