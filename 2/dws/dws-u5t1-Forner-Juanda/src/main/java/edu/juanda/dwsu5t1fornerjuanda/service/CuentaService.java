package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {
    List<CuentaDTO> findAll();
    CuentaDTO findById(Long id);
    List<CuentaDTO> findByPropietario(Long idPropietario);
    void save(CuentaDTO cuentaDto);
    void delete(Long id);
}
