package edu.juanda.dwsu3t1fornerjuanda.controllers;

import edu.juanda.dwsu3t1fornerjuanda.models.Alquiler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CapturaDatosController {

    Logger logger = LoggerFactory.getLogger(CapturaDatosController.class);

    @GetMapping
    public String index(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        logger.info("devolviendo formulario1");
        return "formulario1";
    }

    @PostMapping
    public String procesar(@ModelAttribute Alquiler alquiler) {
        logger.info(alquiler.toString());
        return "formulario2";
    }
}
