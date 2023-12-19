package com.example.daw.web.controller;

import com.example.daw.model.dto.ZonaDTO;
import com.example.daw.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/zonas")
public class ZonaController {

    @Autowired
    ZonaService zonaService;

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView("zonas");
        mav.addObject("zonas", zonaService.findAll());
        return mav;
    }

    @GetMapping
    @RequestMapping("/{idZona}")
    public ModelAndView showZona(@PathVariable Long idZona) {
        ZonaDTO zonaDTO = new ZonaDTO();
        zonaDTO.setId(idZona);
        zonaDTO = zonaService.findById(zonaDTO);

        ModelAndView mav = new ModelAndView("zonashow");
        mav.addObject("zona", zonaDTO);
        return mav;
    }

    @GetMapping
    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("zonaform");
        mav.addObject("add", true);
        mav.addObject("zona", new ZonaDTO());
        return mav;
    }

    @GetMapping
    @RequestMapping("/update/{idZona}")
    public ModelAndView update(@PathVariable Long idZona) {
        ZonaDTO zonaDTO = new ZonaDTO();
        zonaDTO.setId(idZona);
        zonaDTO = zonaService.findById(zonaDTO);

        ModelAndView mav = new ModelAndView("zonaform");
        mav.addObject("add", false);
        mav.addObject("zona", zonaDTO);
        return mav;
    }

    @PostMapping
    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute ZonaDTO zonaDTO) {
        zonaService.save(zonaDTO);
        return new ModelAndView("redirect:/zonas");
    }

    @GetMapping
    @RequestMapping("/delete/{idZona}")
    public ModelAndView delete(@PathVariable Long idZona) {
        ZonaDTO zonaDTO = new ZonaDTO();
        zonaDTO.setId(idZona);
        zonaService.delete(zonaDTO);

        return new ModelAndView("redirect:/zonas");
    }
}
