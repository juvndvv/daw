package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.DireccionDTO;
import edu.juanda.dwsu5FornerJuanda.services.ClienteService;
import edu.juanda.dwsu5FornerJuanda.services.DireccionService;
import edu.juanda.dwsu5FornerJuanda.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("direcciones")
public class DireccionController {
    @Autowired
    DireccionService direccionService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    Validator validator;

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        // Recuperamos la lista de direcciones y el cliente
        List<DireccionDTO> direccionesDTO = direccionService.findAllByCliente(idCliente);
        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        // Mandamos los objetos al modelo
        ModelAndView mav = new ModelAndView("direccion/direcciones");
        mav.addObject("direccionesDTO", direccionesDTO);
        mav.addObject("clienteDTO", clienteDTO);

        return mav;
    }

    @GetMapping("/cliente/{idCliente}/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        ModelAndView mav = new ModelAndView("direccion/direcciones-form");
        mav.addObject("direccionDTO", new DireccionDTO());
        mav.addObject("clienteDTO", clienteDTO);
        return mav;
    }

    @GetMapping("cliente/{idCliente}/select")
    public ModelAndView select(@PathVariable Long idCliente) {
        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        ModelAndView mav = new ModelAndView("direccion/direcciones-select");
        mav.addObject("direccionDTO", new DireccionDTO());
        mav.addObject("direccionesDTO", direccionService.findAll());
        mav.addObject("clienteDTO", clienteDTO);
        return mav;
    }

    @PostMapping("/cliente/{idCliente}/save")
    public ModelAndView save(@PathVariable Long idCliente,
                             @ModelAttribute DireccionDTO direccionDTO) {
        ArrayList<String> errores = new ArrayList<>();

        if (direccionDTO.getId() == null) {
            errores = validator.validate(direccionDTO);
        }

        if (!errores.isEmpty()) {
            ModelAndView mav = new ModelAndView("direccion/direcciones-form");
            mav.addObject("errores", errores);
            mav.addObject("direccionDTO", direccionDTO);
            mav.addObject("clienteDTO", clienteService.findById(idCliente));
            return mav;
        }

        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        clienteDTO.getDireccionesDTO().add(direccionDTO);
        direccionDTO.getClientesDTO().add(clienteDTO);

        direccionService.save(direccionDTO);

        return new ModelAndView("redirect:/direcciones/cliente/{idCliente}");
    }
}