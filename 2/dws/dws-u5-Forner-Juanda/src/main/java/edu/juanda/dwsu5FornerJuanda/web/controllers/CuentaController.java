package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.services.ClienteService;
import edu.juanda.dwsu5FornerJuanda.services.CuentaService;
import edu.juanda.dwsu5FornerJuanda.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Validator validator;

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findByClienteId(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("cuenta/cuentas");
        mav.addObject("clienteDTO", clienteService.findById(idCliente));
        mav.addObject("cuentasDTO", cuentaService.findAllByCliente(idCliente));
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("cuenta/cuenta-show");
        mav.addObject("cuentaDTO", cuentaService.findById(id));
        return mav;
    }

    @GetMapping("/cliente/{idCliente}/create")
    public ModelAndView create(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("cuenta/cuenta-form");

        ClienteDTO clienteDTO = clienteService.findById(idCliente);

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setClienteDTO(clienteDTO);

        mav.addObject("cuentaDTO", cuentaDTO);
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("cuenta/cuenta-form");
        mav.addObject("cuentaDTO", cuentaService.findById(id));
        mav.addObject("add", false);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CuentaDTO cuentaDTO) {
        ArrayList<String> errores = validator.validate(cuentaDTO);
        if (!errores.isEmpty()) {
            cuentaDTO.setClienteDTO(clienteService.findById(cuentaDTO.getClienteDTO().getId()));
            ModelAndView mav = new ModelAndView("cuenta/cuenta-form");
            mav.addObject("cuentaDTO", cuentaDTO);
            mav.addObject("add", true);
            mav.addObject("errores", errores);
            return mav;
        }

        cuentaService.save(cuentaDTO);
        return new ModelAndView("redirect:/cuentas/cliente/" + cuentaDTO.getClienteDTO().getId());
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findByCuentaId(id);
        cuentaService.delete(id);
        return "redirect:/cuentas/cliente/" + clienteDTO.getId();
    }
}
