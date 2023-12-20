package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.MovimientoDTO;
import edu.juanda.dwsu5FornerJuanda.services.CuentaService;
import edu.juanda.dwsu5FornerJuanda.services.MovimientoService;
import edu.juanda.dwsu5FornerJuanda.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("movimientos")
public class MovimientoController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private Validator validator;

    @GetMapping("/cuenta/{idCuenta}")
    public ModelAndView findByCuenta(@PathVariable Long idCuenta) {
        ModelAndView mav = new ModelAndView("movimiento/movimientos");
        mav.addObject(
                "movimientosDTO",
                movimientoService.findByCuenta(idCuenta)
        );
        mav.addObject("cuentaDTO", cuentaService.findById(idCuenta));
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("movimiento/movimiento-show");
        mav.addObject("movimiento", movimientoService.findById(id));
        return mav;
    }

    @GetMapping("/cuenta/{idCuenta}/create")
    public ModelAndView create(@PathVariable Long idCuenta) {
        CuentaDTO cuentaOrigenDTO = new CuentaDTO();
        cuentaOrigenDTO.setId(idCuenta);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setCuentaOrigenDTO(cuentaOrigenDTO);

        System.out.println(cuentaOrigenDTO);

        ModelAndView mav = new ModelAndView("movimiento/movimiento-form");
        mav.addObject("movimientoDTO", movimientoDTO);
        mav.addObject("cuentasDTO", cuentaService.findAll());
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("movimiento/movimiento-form");
        mav.addObject("movimientoDTO", movimientoService.findById(id));
        mav.addObject("add", false);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MovimientoDTO movimientoDTO) {
        ArrayList<String> errores = validator.validate(movimientoDTO);

        if (!errores.isEmpty()) {
            ModelAndView mav = new ModelAndView("movimiento/movimiento-form");
            mav.addObject("movimientoDTO", movimientoDTO);
            mav.addObject("cuentasDTO", cuentaService.findAll());
            mav.addObject("add", true);
            mav.addObject("errores", errores);
            return mav;
        }

        movimientoService.save(movimientoDTO);
        return new ModelAndView(
                "redirect:/movimientos/cuenta/" + movimientoDTO.getCuentaOrigenDTO().getId());
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("Borrando el movimiento");
        Long idCuentaOrigen = movimientoService.findById(id).getCuentaOrigenDTO().getId();
        movimientoService.delete(id);
        return "redirect:/movimientos/cuenta/" + idCuentaOrigen;
    }
}
