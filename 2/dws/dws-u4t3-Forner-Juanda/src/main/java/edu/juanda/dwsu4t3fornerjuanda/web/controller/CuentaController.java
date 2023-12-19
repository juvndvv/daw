package edu.juanda.dwsu4t3fornerjuanda.web.controller;

import edu.juanda.dwsu4t3fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t3fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu4t3fornerjuanda.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {
    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{idCuenta}")
    public ModelAndView findById(@PathVariable Long idCuenta) {
        log.info("CuentaController - findById: Buscando cuenta con id: " + idCuenta);

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(idCuenta);
        cuentaDTO = cuentaService.findById(cuentaDTO);

        ModelAndView mav = new ModelAndView("cuenta/cuentashow");
        mav.addObject("cuentaDTO", cuentaDTO);

        return mav;
    }

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findByCliente(@PathVariable Long idCliente) {
        log.info("CuentaController - findBCliente: Buscando cuentas de cliente con id: " + idCliente);

        // Crea el clienteDTO
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);

        List<CuentaDTO> cuentaDTOS = cuentaService.findByClient(clienteDTO);

        // Si no se ha encontrado el cliente se redirige a clientes
        if (cuentaDTOS == null) {
            return new ModelAndView("redirect:/clientes");
        }

        ModelAndView mav = new ModelAndView("cuenta/cuentas");
        mav.addObject("idCliente", idCliente);
        mav.addObject("cuentas", cuentaDTOS);
        return mav;
    }

    // Alta de cuenta
    @GetMapping("/cliente/{idCliente}/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("CuentaController - add: Añadimos una nueva cuenta");

        ModelAndView mav = new ModelAndView("cuenta/cuentaform");

        // Crea un CuentaDTO y su propietario
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setPropietario(clienteDTO);

        // Añade los objetos al modelo
        mav.addObject("add", true);
        mav.addObject("idCliente", idCliente);
        mav.addObject("cuentaDTO", cuentaDTO);

        return mav;
    }

    // Actualizacion de cuenta
    @GetMapping("/update/{idCuenta}")
    public ModelAndView update(@PathVariable Long idCuenta) {
        log.info("CuentaController - update: Modificamos la cuenta: " + idCuenta);

        ModelAndView mav = new ModelAndView("cuenta/cuentaform");

        // Obtiene la CuentaDTO
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(idCuenta);
        cuentaDTO = cuentaService.findById(cuentaDTO);

        // Añade los objetos al modelo
        mav.addObject("add", false);
        mav.addObject("cuentaDTO", cuentaDTO);

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CuentaDTO cuentaDTO) {
        log.info("CuentaController - save: se inicia el guardado del cliente");

        List<String> errores = cuentaService.save(cuentaDTO);

        if (!errores.isEmpty()) {
            log.warn("CuentaController - save: hubieron errores al guardar la cuenta: " + errores);

            ModelAndView mav = new ModelAndView("cliente/clienteform");
            mav.addObject("cuentaDTO", cuentaDTO);
            mav.addObject("add", true);
            mav.addObject("errores", errores);

            return mav;
        }

        return new ModelAndView("redirect:/cuentas/cliente/" + cuentaDTO.getPropietario().getId());
    }

    @GetMapping("delete/{idCuenta}")
    public ModelAndView delete(@PathVariable Long idCuenta) {
        log.info("CuentaController - delete: Borramos la cuenta: " + idCuenta);

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(idCuenta);

        Long idCliente = cuentaService.findById(cuentaDTO).getPropietario().getId();

        cuentaDTO.setId(idCuenta);
        cuentaService.delete(cuentaDTO);

        // Redireccionamos a las cuentas del cliente
        return new ModelAndView("redirect:/cuentas/cliente/"+idCliente);
    }
}
