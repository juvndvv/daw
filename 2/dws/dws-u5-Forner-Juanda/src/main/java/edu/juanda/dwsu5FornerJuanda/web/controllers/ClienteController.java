package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView findAll() {
        log.info("ClienteController - findAll: Mostramos todos los clientes");

        ModelAndView mav = new ModelAndView("cliente/clientes");
        List<ClienteDTO> listaClientesDTO = clienteService.findAll();
        mav.addObject("listaClientesDto", listaClientesDTO);

        return mav;
    }

    // Visualizar la información de un cliente
    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        log.info("ClienteController - findById: Mostramos la informacion del cliente:" +
                id);

        ClienteDTO clienteDTO = clienteService.findById(id);

        ModelAndView mav = new ModelAndView("cliente/cliente-show");
        mav.addObject("clienteDTO", clienteDTO);

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("cliente/cliente-form");
        mav.addObject("clienteDTO", new ClienteDTO());
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("cliente/cliente-form");
        mav.addObject("clienteDTO", clienteService.findById(id));
        mav.addObject("add", false);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
        return new ModelAndView("redirect:/clientes");
    }

    // Borrar un cliente
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {

        log.info("ClienteController - delete: Borramos el cliente:" + id);

        clienteService.delete(id);

        return new ModelAndView("redirect:/clientes");
    }
}
