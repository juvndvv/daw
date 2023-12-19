package edu.juanda.dwsu5t1fornerjuanda.web.controller;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.DireccionDTO;
import edu.juanda.dwsu5t1fornerjuanda.service.ClienteService;
import edu.juanda.dwsu5t1fornerjuanda.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("direcciones")
public class DireccionController {
    private static final Logger log = LoggerFactory.getLogger(DireccionController.class);

    @Autowired
    DireccionService direccionService;

    @Autowired
    ClienteService clienteService;

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info("DireccionController - findAllByCliente: Listamos las direcciones del cliente " + idCliente);

        // Recuperamos la lista de direcciones y el cliente
        List<DireccionDTO> listaDireccionesDto = direccionService.findAllByCliente(idCliente);
        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        // Mandamos los objetos al modelo
        ModelAndView mav = new ModelAndView("direccion/direcciones");
        mav.addObject("direcciones", listaDireccionesDto);
        mav.addObject("cliente", clienteDTO);

        return mav;
    }

    @GetMapping("/cliente/{idCliente}/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("DireccionController - add: Añadimos una nueva direccion al cliente " + idCliente);

        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        ModelAndView mav = new ModelAndView("direccion/direccionesform");
        mav.addObject("direccion", new DireccionDTO());
        mav.addObject("cliente", clienteDTO);
        return mav;
    }

    @PostMapping("/cliente/{idCliente}/save")
    public ModelAndView save(@PathVariable Long idCliente,
                             @ModelAttribute("direccion") DireccionDTO direccionDTO) {
        log.info("DireccionController - save: Almacenamos la direccion: " + direccionDTO.toString());

        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        clienteDTO.getDireccionesDTO().add(direccionDTO);
        direccionDTO.getListaClientesDTO().add(clienteDTO);
        direccionService.save(direccionDTO);

        return new ModelAndView("redirect:/direcciones/cliente/{idCliente}");
    }
}
