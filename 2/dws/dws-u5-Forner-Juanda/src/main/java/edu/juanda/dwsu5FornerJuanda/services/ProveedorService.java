package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.ProveedorDTO;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> findAll();
    ProveedorDTO findById(Long id);
    List<ProveedorDTO> findByIdCliente(Long idCliente);
    void save(ProveedorDTO proveedorDTO);
    void delete(Long id);
}
