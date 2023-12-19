package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoService {
    List<MovimientoDTO> findAll();
    List<MovimientoDTO> findByCuentaOrigenIdOrCuentaDestinoId(Long idCuentaOrigen, Long idCuentaDestino);
    MovimientoDTO findById(Long id);
    void save(MovimientoDTO movimientoDto);
    void delete(Long id);
}
