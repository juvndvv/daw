package edu.juanda.tarea1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CapturaDatosController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "formulario1.html";
    }

    @GetMapping("/captura-datos/{userId}")
    @ResponseBody
    public String capturaDatos(@PathVariable String userId) {
        return userId;
    }

    @PostMapping("/captura-datos/{userId}")
    @ResponseBody
    public String capturaDatosPost(@PathVariable String userId) {
        return "userId: " + userId + " (POST)";
    }
}
