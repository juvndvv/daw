package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoService {
    List<MovimientoDTO> findByCuenta(Long idCuenta);
    MovimientoDTO findById(Long id);
    void save(MovimientoDTO movimientoDTO);
    void delete(Long id);
}
