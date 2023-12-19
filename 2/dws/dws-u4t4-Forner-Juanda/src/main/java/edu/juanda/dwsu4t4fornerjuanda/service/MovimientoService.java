package edu.juanda.dwsu4t4fornerjuanda.service;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoService {
    List<MovimientoDTO> findAll();
    MovimientoDTO findById(MovimientoDTO movimientoDTO);
    List<MovimientoDTO> findByCuenta(CuentaDTO cuentaDTO);
    List<String> save(MovimientoDTO movimientoDTO);
    void delete(MovimientoDTO movimientoDTO);
}
