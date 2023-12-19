package edu.juanda.dwsu5t1fornerjuanda.web.controller;

import java.util.List;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.RecomendacionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    // Listar los clientes
    @GetMapping
    public ModelAndView findAll() {
        log.info("ClienteController - findAll: Mostramos todos los clientes");

        ModelAndView mav = new ModelAndView("cliente/clientes");
        List<ClienteDTO> listaClientesDTO = clienteService.findAll();
        mav.addObject("listaClientesDto", listaClientesDTO);

        return mav;
    }

    // Visualizar la información de un cliente
    @GetMapping
    @RequestMapping("/{idCliente}")
    public ModelAndView findById(@PathVariable("idCliente") Long idCliente) {
        log.info("ClienteController - findById: Mostramos la informacion del cliente:" +
                idCliente);

        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        ModelAndView mav = new ModelAndView("cliente/clienteshow");
        mav.addObject("clienteDto", clienteDTO);

        return mav;
    }

    @GetMapping
    @RequestMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("cliente/clienteform");
        mav.addObject("clienteDto", new ClienteDTO());
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping
    @RequestMapping("/update/{idCliente}")
    public ModelAndView update(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("cliente/clienteform");
        mav.addObject("clienteDto", clienteService.findById(idCliente));
        mav.addObject("add", false);
        return mav;
    }

    @PostMapping
    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute("clienteDto") ClienteDTO clienteDTO) {
        log.info("ClienteController - Guardamos el cliente: " + clienteDTO);
        clienteService.save(clienteDTO);
        return new ModelAndView("redirect:/clientes");
    }

    // Borrar un cliente
    @GetMapping
    @RequestMapping("/delete/{idCliente}")
    public ModelAndView delete(@PathVariable("idCliente") Long idCliente) {

        log.info("ClienteController - delete: Borramos el cliente:" + idCliente);

        clienteService.delete(idCliente);

        return new ModelAndView("redirect:/clientes");
    }
}
