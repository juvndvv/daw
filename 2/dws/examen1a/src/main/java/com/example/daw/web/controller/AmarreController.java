package com.example.daw.web.controller;

import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.ZonaDTO;
import com.example.daw.service.AmarreService;
import com.example.daw.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/amarres")
public class AmarreController {

    @Autowired
    AmarreService amarreService;

    @Autowired
    ZonaService zonaService;

    @GetMapping
    @RequestMapping("/zona/{idZona}")
    public ModelAndView showAmarresDeZona(@PathVariable Long idZona) {
        ZonaDTO zonaDTO = new ZonaDTO();
        zonaDTO.setId(idZona);
        zonaDTO = zonaService.findById(zonaDTO);

        List<AmarreDTO> amarreDTOS = amarreService.findByIdZona(zonaDTO);

        ModelAndView mav = new ModelAndView("amarres");
        mav.addObject("zona", zonaDTO);
        mav.addObject("amarres", amarreDTOS);
        return mav;
    }

    @GetMapping
    @RequestMapping("/delete/{idAmarre}")
    public ModelAndView delete(@PathVariable Long idAmarre) {
        AmarreDTO amarreDTO = new AmarreDTO();
        amarreDTO.setId(idAmarre);

        amarreService.delete(amarreDTO);

        return new ModelAndView("redirect:/zonas");
    }
}
