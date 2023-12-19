package com.example.daw.service;


import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.ZonaDTO;
import com.example.daw.repository.dao.AmarreRepository;
import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmarreServiceImpl implements AmarreService {

    @Autowired
    AmarreRepository amarreRepository;

    @Override
    public List<AmarreDTO> findByIdZona(ZonaDTO zonaDTO) {
        List<Amarre> amarres = amarreRepository.findByIdZona(ZonaDTO.toEntity(zonaDTO));

        List<AmarreDTO> amarreDTOS = new ArrayList<>();
        for (Amarre amarre : amarres) {
            amarreDTOS.add(AmarreDTO.toDTO(amarre, zonaDTO));
        }

        return amarreDTOS;
    }

    @Override
    public void delete(AmarreDTO amarreDTO) {
        amarreRepository.delete(AmarreDTO.toEntity(amarreDTO, new Zona()));
    }
}
