package com.example.daw.service;


import com.example.daw.model.dto.ZonaDTO;
import com.example.daw.repository.dao.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaServiceImpl implements ZonaService{

    @Autowired
    ZonaRepository zonaRepository;

    @Override
    public List<ZonaDTO> findAll() {
        return zonaRepository.findAll().stream().map(ZonaDTO::toDTO).toList();
    }

    @Override
    public ZonaDTO findById(ZonaDTO zonaDTO) {
        return ZonaDTO.toDTO(zonaRepository.findById(ZonaDTO.toEntity(zonaDTO)));
    }

    @Override
    public void save(ZonaDTO zonaDTO) {
        zonaRepository.save(ZonaDTO.toEntity(zonaDTO));
    }

    @Override
    public void delete(ZonaDTO zonaDTO) {
        zonaRepository.delete(ZonaDTO.toEntity(zonaDTO));
    }
}
