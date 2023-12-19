package edu.juanda.dwsu3t4fornerjuanda.controllers;

import edu.juanda.dwsu3t4fornerjuanda.models.Pedido;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/pedidopizza")
    public String showPedido(@ModelAttribute Pedido pedido) {
        return "pedidoPizza";
    }

    @PostMapping("/pedidopizza")
    public String procesarPedido(@ModelAttribute Pedido pedido) {
        return "muestraPedido";
    }
}
