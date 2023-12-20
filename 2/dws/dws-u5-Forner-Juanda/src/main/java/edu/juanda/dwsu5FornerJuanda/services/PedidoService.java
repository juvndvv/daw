package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {
    List<PedidoDTO> findByCliente(Long idCliente);
    List<PedidoDTO> findByProveedor(Long idProveedor);
    void save(PedidoDTO pedidoDTO);
    void delete(Long id);
}
