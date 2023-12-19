package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(Long idCliente);
    void save(ClienteDTO clienteDto);
    void delete(Long idCliente);

}
