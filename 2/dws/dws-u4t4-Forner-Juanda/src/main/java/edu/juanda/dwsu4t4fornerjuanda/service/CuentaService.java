package edu.juanda.dwsu4t4fornerjuanda.service;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {
    List<CuentaDTO> findAll();
    CuentaDTO findById(CuentaDTO cuentaDTO);
    List<CuentaDTO> findByClient(ClienteDTO clienteDTO);
    List<String> save(CuentaDTO cuentaDTO);
    void delete(CuentaDTO cuentaDTO);
}
