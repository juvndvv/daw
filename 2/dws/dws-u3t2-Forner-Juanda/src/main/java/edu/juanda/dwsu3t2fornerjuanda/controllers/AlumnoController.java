package edu.juanda.dwsu3t2fornerjuanda.controllers;

import edu.juanda.dwsu3t2fornerjuanda.entitites.Alumno;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class AlumnoController {
    private final ArrayList<Alumno> alumnos = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @PostConstruct
    public void init() {
        alumnos.add(new Alumno("Juan", "Pérez", "García", "12345678A"));
        alumnos.add(new Alumno("María", "González", "López", "12345678B"));
        alumnos.add(new Alumno("Luis", "García", "Pérez", "12345678C"));
    }

    @GetMapping
    public String buscarAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "comprobarDNI";
    }

    @PostMapping
    public String procesarPeticion(@ModelAttribute Alumno alumno, Model model) {
        Alumno alumnoEncontrado = findAlumnoByDni(alumno.getDni());

        if (alumnoEncontrado.getDni() == null) {
            model.addAttribute("mensaje", "Alumno no encontrado");
            return "comprobarDNI";
        }

        model.addAttribute("alumno", alumnoEncontrado);
        return "muestraDatosAlumno";
    }

    // FIX: Esto deberia ser un servicio
    public Alumno findAlumnoByDni(String dni) {
        logger.info("Buscando alumno con DNI: " + dni);

        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {
                logger.info("Alumno encontrado: " + alumno);
                return alumno;
            }
        }

        logger.info("Alumno no encontrado");
        return new Alumno();
    }
}
