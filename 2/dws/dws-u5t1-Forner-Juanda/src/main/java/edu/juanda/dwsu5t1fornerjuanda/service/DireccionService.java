package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.DireccionDTO;

import java.util.List;

public interface DireccionService {
    public List<DireccionDTO> findAll();
    public List<DireccionDTO> findAllByCliente(Long idCliente);
    public void save(DireccionDTO direccionDTO);
}
