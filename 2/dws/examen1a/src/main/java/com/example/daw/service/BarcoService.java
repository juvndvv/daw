package com.example.daw.service;


import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.BarcoDTO;

import java.util.List;

public interface BarcoService {
    List<BarcoDTO> findByIdAmarre(AmarreDTO amarreDTO);
}
