package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.DireccionDTO;

import java.util.List;

public interface DireccionService {
    public List<DireccionDTO> findAll();
    public List<DireccionDTO> findAllByCliente(Long idCliente);
    Boolean existsById(Long id);
    public void save(DireccionDTO direccionDTO);
}
