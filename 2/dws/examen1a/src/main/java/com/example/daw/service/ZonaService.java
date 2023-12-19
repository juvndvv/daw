package com.example.daw.service;


import com.example.daw.model.dto.ZonaDTO;

import java.util.List;

public interface ZonaService {
    List<ZonaDTO> findAll();
    ZonaDTO findById(ZonaDTO zonaDTO);
    void save(ZonaDTO zonaDTO);
    void delete(ZonaDTO zonaDTO);
}
