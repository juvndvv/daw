package edu.juanda.dwsu5t1fornerjuanda.web.controller;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu5t1fornerjuanda.service.CuentaService;
import edu.juanda.dwsu5t1fornerjuanda.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {

    Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("movimiento/movimientos");
        mav.addObject("listaMovimientosDto", movimientoService.findAll());
        return mav;
    }

    @GetMapping
    @RequestMapping("/cuenta/{idCuenta}")
    public ModelAndView findByCuentaOrigenOrCuentaDestino(@PathVariable Long idCuenta) {
        ModelAndView mav = new ModelAndView("movimiento/movimientos");
        mav.addObject("listaMovimientosDto", movimientoService.findByCuentaOrigenIdOrCuentaDestinoId(idCuenta, idCuenta));
        mav.addObject("cuentaDto", cuentaService.findById(idCuenta));   // Cuenta de la que se han buscado los movimientos
        return mav;
    }

    @GetMapping
    @RequestMapping("/{idMovimiento}")
    public ModelAndView findById(@PathVariable Long idMovimiento) {
        ModelAndView mav = new ModelAndView("movimiento/movimientoshow");
        mav.addObject("movimiento", movimientoService.findById(idMovimiento));
        return mav;
    }

    @GetMapping
    @RequestMapping("/cuenta/{idCuenta}/create")
    public ModelAndView create(@PathVariable Long idCuenta) {
        log.info("MovimientoController - create: creando un movimiento");

        CuentaDTO cuentaDTO = cuentaService.findById(idCuenta);
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setCuentaOrigenDto(cuentaDTO);

        ModelAndView mav = new ModelAndView("movimiento/movimientoform");
        mav.addObject("movimientoDTO", movimientoDTO);
        mav.addObject("cuentasDTO", cuentaService.findAll());

        mav.addObject("add", true);
        return mav;
    }

    @PostMapping
    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute MovimientoDTO movimientoDTO) {
        log.info("MovimientoController - save: guardando: " + movimientoDTO);

        // Guardamos el movimiento
        movimientoService.save(movimientoDTO);

        return new ModelAndView("redirect:/movimientos/cuenta/" + movimientoDTO.getCuentaOrigenDto().getId());
    }

    @GetMapping
    @RequestMapping("/delete/{idMovimiento}")
    public String delete(@PathVariable  Long idMovimiento) {
        log.info("MovimientoController - delete: borrando movimiento con id: " + idMovimiento);

        MovimientoDTO movimientoDTO = movimientoService.findById(idMovimiento);
        Long idCuenta = movimientoDTO.getCuentaOrigenDto().getId();
        movimientoService.delete(idMovimiento);
        return "redirect:/movimientos/cuenta/" + idCuenta;
    }
}
