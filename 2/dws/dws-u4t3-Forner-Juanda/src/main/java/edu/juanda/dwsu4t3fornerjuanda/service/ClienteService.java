package edu.juanda.dwsu4t3fornerjuanda.service;

import edu.juanda.dwsu4t3fornerjuanda.model.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(ClienteDTO clienteDTO);
    List<String> save(ClienteDTO clienteDTO);
    void delete(ClienteDTO clienteDTO);

}
