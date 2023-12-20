package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.ClienteMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.ClienteRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> clienteMapper.toDTO(cliente))
                .toList();
    }

    @Override
    public ClienteDTO findById(Long id) {
        return clienteMapper.toDTO(
                clienteRepository.findById(id)
                        .orElse(new Cliente())
        );
    }

    @Override
    public void save(ClienteDTO clienteDTO) {
        clienteRepository.save(clienteMapper.toEntity(clienteDTO));
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
