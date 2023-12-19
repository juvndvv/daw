package com.example.daw.web.controller;

import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.BarcoDTO;
import com.example.daw.service.BarcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/barcos")
public class BarcoController {

    @Autowired
    BarcoService barcoService;

    @GetMapping
    @RequestMapping("/amarre/{idAmarre}")
    public ModelAndView findByIdAmarre(@PathVariable Long idAmarre) {
        AmarreDTO amarreDTO = new AmarreDTO();
        amarreDTO.setId(idAmarre);

        List<BarcoDTO> barcoDTOS = barcoService.findByIdAmarre(amarreDTO);

        ModelAndView mav = new ModelAndView("barcos");
        mav.addObject("barcos", barcoDTOS);
        return mav;
    }
}
