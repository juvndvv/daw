package edu.juanda.dwsu5FornerJuanda.services;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(Long id);
    ClienteDTO findByCuentaId(Long idCuenta);
    void save(ClienteDTO clienteDTO);
    void delete(Long idCliente);
}
