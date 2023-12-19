package edu.juanda.dwsu5t1fornerjuanda.web.controller;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu5t1fornerjuanda.service.ClienteService;
import edu.juanda.dwsu5t1fornerjuanda.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaService cuentaService;

    // Muestra todas las cuentas
    @GetMapping
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("cuenta/cuentas");
        mav.addObject("listaCuentasDto", cuentaService.findAll());
        return mav;
    }

    // Muestra el detalle de una cuenta
    @GetMapping
    @RequestMapping("/{idCuenta}")
    public ModelAndView findById(@PathVariable Long idCuenta) {
        ModelAndView mav = new ModelAndView("cuenta/cuentashow");
        mav.addObject("cuentaDto", cuentaService.findById(idCuenta));
        return mav;
    }

    // Muestra las cuentas de un cliente
    @GetMapping
    @RequestMapping("/cliente/{idCliente}")
    public ModelAndView findByPropietario(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("cuenta/cuentas");
        mav.addObject("propietario", clienteService.findById(idCliente));
        mav.addObject("listaCuentasDto", cuentaService.findByPropietario(idCliente));
        return mav;
    }

    // Crea una cuenta a un cliente
    @GetMapping
    @RequestMapping("/cliente/{idCliente}/create")
    public ModelAndView create(@PathVariable Long idCliente) {
        ClienteDTO propietario = clienteService.findById(idCliente);

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setPropietarioDto(propietario);

        ModelAndView mav = new ModelAndView("cuenta/cuentaform");
        mav.addObject("cuentaDTO", cuentaDTO);
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping
    @RequestMapping("/update/{idCuenta}")
    public ModelAndView update(@PathVariable Long idCuenta) {
        CuentaDTO cuentaDTO = cuentaService.findById(idCuenta);

        ModelAndView mav = new ModelAndView("cuenta/cuentaform");
        mav.addObject("cuentaDTO", cuentaDTO);
        mav.addObject("add", false);
        return mav;
    }

    // Guarda cuentas
    @PostMapping
    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute CuentaDTO cuentaDTO) {
        cuentaService.save(cuentaDTO);

        return new ModelAndView("redirect:/cuentas/cliente/"
                + cuentaDTO.getPropietarioDto().getId());
    }

    // Borra cuentas
    @GetMapping
    @RequestMapping("/delete/{idCuenta}")
    public ModelAndView delete(@PathVariable Long idCuenta) {
        cuentaService.delete(idCuenta);
        return new ModelAndView("redirect:/cuentas");
    }
}
