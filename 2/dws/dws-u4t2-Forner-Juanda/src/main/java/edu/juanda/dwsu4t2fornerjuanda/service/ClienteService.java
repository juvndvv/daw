package edu.juanda.dwsu4t2fornerjuanda.service;

import edu.juanda.dwsu4t2fornerjuanda.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(ClienteDTO clienteDTO);
    List<String> save(ClienteDTO clienteDTO);
    void delete(ClienteDTO clienteDTO);

}
