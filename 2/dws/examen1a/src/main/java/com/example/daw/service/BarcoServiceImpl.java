package com.example.daw.service;

import com.example.daw.model.dto.AmarreDTO;
import com.example.daw.model.dto.BarcoDTO;
import com.example.daw.repository.dao.BarcoRepository;
import com.example.daw.repository.entity.Barco;
import com.example.daw.repository.entity.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarcoServiceImpl implements BarcoService {

    @Autowired
    BarcoRepository barcoRepository;

    @Override
    public List<BarcoDTO> findByIdAmarre(AmarreDTO amarreDTO) {
        List<Barco> barcos = barcoRepository.findByIdAmarre(AmarreDTO.toEntity(amarreDTO, new Zona()));

        List<BarcoDTO> barcoDTOS = new ArrayList<>();
        for (Barco  barco : barcos)
            barcoDTOS.add(BarcoDTO.toDTO(barco, new AmarreDTO()));

        return barcoDTOS;
    }
}
