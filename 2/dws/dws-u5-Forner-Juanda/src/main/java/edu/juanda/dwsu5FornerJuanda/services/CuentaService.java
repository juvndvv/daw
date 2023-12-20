package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {
    List<CuentaDTO> findAll();
    List<CuentaDTO> findAllByCliente(Long idCliente);
    CuentaDTO findById(Long id);
    void save(CuentaDTO cuentaDTO);
    void delete(Long id);
}
