package edu.juanda.dwsu4t3fornerjuanda.service;

import edu.juanda.dwsu4t3fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t3fornerjuanda.model.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {
    CuentaDTO findById(CuentaDTO cuentaDTO);
    List<CuentaDTO> findByClient(ClienteDTO clienteDTO);
    List<String> save(CuentaDTO cuentaDTO);
    void delete(CuentaDTO cuentaDTO);
}
