package com.example.daw.service;


import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.ZonaDTO;

import java.util.List;

public interface AmarreService {
    List<AmarreDTO> findByIdZona(ZonaDTO zonaDTO);
    void delete(AmarreDTO amarreDTO);
}
