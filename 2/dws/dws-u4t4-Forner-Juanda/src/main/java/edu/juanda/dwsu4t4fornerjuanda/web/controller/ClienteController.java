package edu.juanda.dwsu4t4fornerjuanda.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t4fornerjuanda.service.ClienteService;

@Controller
public class ClienteController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

    // Listar los clientes
    @GetMapping("/clientes")
    public ModelAndView findAll() {

        log.info("ClienteController - findAll: Mostramos todos los clientes");

        ModelAndView mav = new ModelAndView("cliente/clientes");
        List<ClienteDTO> listaClientesDTO = clienteService.findAll();
        mav.addObject("listaClientesDTO", listaClientesDTO);

        return mav;
    }

    // Visualizar la informacion de un cliente
    @GetMapping("/clientes/{idCliente}")
    public ModelAndView findById(@PathVariable("idCliente") Long idCliente) {

        log.info("ClienteController - findById: Mostramos la informacion del cliente:" +
                idCliente);

        // Obtenemos el cliente y lo pasamos al modelo
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);
        clienteDTO = clienteService.findById(clienteDTO);

        ModelAndView mav = new ModelAndView("cliente/clienteshow");
        mav.addObject("clienteDTO", clienteDTO);

        return mav;
    }


    // Alta de clientes
    @GetMapping("/clientes/add")
    public ModelAndView add() {

        log.info("ClienteController - add: Anyadimos un nuevo cliente");

        ModelAndView mav = new ModelAndView("cliente/clienteform");
        mav.addObject("clienteDTO", new ClienteDTO());
        mav.addObject("add", true);

        return mav;
    }

    // Salvar clientes
    @PostMapping("/clientes/save")
    public ModelAndView save(@ModelAttribute("clienteDTO") ClienteDTO clienteDTO) {
        List<String> errores = clienteService.save(clienteDTO);

        if (!errores.isEmpty()) {
            ModelAndView mav = new ModelAndView("cliente/clienteform");
            mav.addObject("clienteDTO", clienteDTO);
            mav.addObject("add", true);
            mav.addObject("errores", errores);

            return mav;
        }

        log.info("ClienteController - save: Salvamos los datos del cliente:" + clienteDTO.toString());
        return new ModelAndView("redirect:/clientes");
    }

    // Actualizar la informacion de un cliente
    @GetMapping("/clientes/update/{idCliente}")
    public ModelAndView update(@PathVariable("idCliente") Long idCliente) {

        log.info("ClienteController - update: Modificamos el cliente: " + idCliente);

        // Obtenemos el cliente y lo pasamos al modelo para ser actualizado
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);
        clienteDTO = clienteService.findById(clienteDTO);

        ModelAndView mav = new ModelAndView("cliente/clienteform");
        mav.addObject("clienteDTO", clienteDTO);
        mav.addObject("add", false);

        return mav;
    }

    // Borrar un cliente
    @GetMapping("/clientes/delete/{idCliente}")
    public ModelAndView delete(@PathVariable("idCliente") Long idCliente) {

        log.info("ClienteController - delete: Borramos el cliente:" + idCliente);

        // Creamos un cliente y le asignamos el id. Este cliente es el que se va a borrar
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);
        clienteService.delete(clienteDTO);

        // Redireccionamos para volver a invocar al metodo que escucha /clientes

        return new ModelAndView("redirect:/clientes");
    }
}
