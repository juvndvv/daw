package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ProveedorDTO;
import edu.juanda.dwsu5FornerJuanda.services.ProveedorService;
import edu.juanda.dwsu5FornerJuanda.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @Autowired
    private Validator validator;

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("proveedor/proveedores");
        mav.addObject("proveedoresDTO", proveedorService.findAll());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        ProveedorDTO proveedorDTO = proveedorService.findById(id);

        ModelAndView mav = new ModelAndView("proveedor/proveedor-show");
        mav.addObject("proveedorDTO", proveedorDTO);
        return mav;
    }

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findByIdCliente(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("proveedor/proveedores");
        mav.addObject("proveedoresDTO", proveedorService.findByIdCliente(idCliente));
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("proveedor/proveedor-form");
        mav.addObject("proveedorDTO", new ProveedorDTO());
        mav.addObject("add", true);
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("proveedor/proveedor-form");
        mav.addObject("proveedorDTO", proveedorService.findById(id));
        mav.addObject("add", false);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ProveedorDTO proveedorDTO) {
        ArrayList<String> errores = validator.validate(proveedorDTO);

        if (!errores.isEmpty()) {
            ModelAndView mav = new ModelAndView("proveedor/proveedor-form");
            mav.addObject("proveedorDTO", proveedorDTO);
            mav.addObject("errores", errores);
            mav.addObject("add", proveedorDTO.getId() == null);
            return mav;
        }

        proveedorService.save(proveedorDTO);
        return new ModelAndView("redirect:/proveedores");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        proveedorService.delete(id);
        return "redirect:/proveedores";
    }
}
