package edu.juanda.dwsu4t4fornerjuanda.web.controller;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu4t4fornerjuanda.service.ClienteService;
import edu.juanda.dwsu4t4fornerjuanda.service.CuentaService;
import edu.juanda.dwsu4t4fornerjuanda.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {
    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @Autowired
    ClienteService clienteService;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ModelAndView findAll() {
        log.info("MovimientoController - findAll: Buscando movimientos");

        List<MovimientoDTO> movimientos = movimientoService.findAll();

        ModelAndView mav = new ModelAndView("movimiento/movimientos");
        mav.addObject("movimientos", movimientos);
        return mav;
    }

    @GetMapping("/{idMovimiento}")
    public ModelAndView findById(@PathVariable Long idMovimiento) {
        log.info("MovimientoController - findById: Buscando movimiento con id: " + idMovimiento);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(idMovimiento);
        movimientoDTO = movimientoService.findById(movimientoDTO);

        ModelAndView mav = new ModelAndView("movimiento/movimientoshow");
        mav.addObject("movimientoDTO", movimientoDTO);

        return mav;
    }

    @GetMapping("/cuenta/{idCuenta}")
    public ModelAndView findByCuenta(@PathVariable Long idCuenta) {
        log.info("MovimientoController - findByCuenta: Buscando movimiento");

        // Crea el DTO
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(idCuenta);
        cuentaDTO = cuentaService.findById(cuentaDTO);

        List<MovimientoDTO> movimientoDTOS = movimientoService.findByCuenta(cuentaDTO);

        ModelAndView mav = new ModelAndView("movimiento/movimientos");
        mav.addObject("cuentaDTO", cuentaDTO);
        mav.addObject("movimientos", movimientoDTOS);

        return mav;
    }

    @GetMapping("/cuenta/{idCuenta}/add")
    public ModelAndView add(@PathVariable Long idCuenta) {
        log.info("MovimientoController - add: Añadimos un nuevo movimiento");

        ModelAndView mav = new ModelAndView("movimiento/movimientoform");

        // Crea un MovimientoDTO
        CuentaDTO cuentaOrigenDTO = new CuentaDTO();
        cuentaOrigenDTO.setId(idCuenta);
        cuentaOrigenDTO = cuentaService.findById(cuentaOrigenDTO);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setCuentaOrigen(cuentaOrigenDTO);
        movimientoDTO.setCuentaDestino(new CuentaDTO());

        // Busca las cuentas disponibles y elimina la propia cuenta
        List<CuentaDTO> cuentas = cuentaService.findAll();

        // Añade los objetos al modelo
        mav.addObject("add", true);
        mav.addObject("movimientoDTO", movimientoDTO);
        mav.addObject("cuentasDTO", cuentas);

        return mav;
    }

    @GetMapping("/update/{idMovimiento}")
    public ModelAndView update(@PathVariable Long idMovimiento) {
        log.info("MovimientoController - update: Modificamos el movimiento: " + idMovimiento);

        ModelAndView mav = new ModelAndView("movimiento/movimientoform");

        // Obtienes el MovimientoDTO
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(idMovimiento);
        movimientoDTO = movimientoService.findById(movimientoDTO);

        // Añade los objetos al modelo
        mav.addObject("add", false);
        mav.addObject("movimientoDTO", movimientoDTO);

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MovimientoDTO movimientoDTO) {
        log.info("MovimientoController - save: se inicia el guardado del movimiento");
        System.out.println(movimientoDTO.getCuentaOrigen());
        System.out.println(movimientoDTO.getCuentaDestino());
        List<String> errores = movimientoService.save(movimientoDTO);

        if (!errores.isEmpty()) {
            log.warn("CuentaController - save: hubieron errores al guardar el movimiento");

            ModelAndView mav = new ModelAndView("movimiento/movimientoform");
            mav.addObject("movimientoDTO", movimientoDTO);
            mav.addObject("add", true);
            mav.addObject("errores", errores);

            return mav;
        }

        return new ModelAndView("redirect:/movimientos/cuenta/" + movimientoDTO.getCuentaOrigen().getId());
    }

    @GetMapping("delete/{idMovimiento}")
    public ModelAndView delete(@PathVariable Long idMovimiento) {
        log.info("MovimientoController - delete: Borramos el movimiento: " + idMovimiento);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(idMovimiento);
        movimientoDTO = movimientoService.findById(movimientoDTO);

        movimientoService.delete(movimientoDTO);

        return new ModelAndView("redirect:/movimientos/cuenta/" + movimientoDTO.getCuentaOrigen().getId());
    }
}
