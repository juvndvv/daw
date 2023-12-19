package edu.juanda.dwsu4t1fornerjuanda.service;

import edu.juanda.dwsu4t1fornerjuanda.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(ClienteDTO clienteDTO);
    void save(ClienteDTO clienteDTO);
    void delete(ClienteDTO clienteDTO);

}
