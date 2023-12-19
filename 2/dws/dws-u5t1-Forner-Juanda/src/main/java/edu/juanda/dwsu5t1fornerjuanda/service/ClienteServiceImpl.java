package edu.juanda.dwsu5t1fornerjuanda.service;

import java.util.List;

import edu.juanda.dwsu5t1fornerjuanda.exceptions.ClienteNotFoundException;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.ClienteMapper;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.ClienteRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.RecomendacionRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Recomendacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> findAll() {
        log.info("ClienteServiceImpl - findAll: Lista de todos los cliente");

        return clienteRepository.findAll()
                .stream()
                .map(cliente -> clienteMapper.toDto(cliente))
                .toList();
    }

    @Override
    public ClienteDTO findById(Long idCliente) {
        log.info("ClienteServiceImpl - findById: Buscar cliente por id: " +
                idCliente);

        return clienteRepository.findById(idCliente)
                .map(value -> clienteMapper.toDto(value))
                .orElseThrow(() -> new ClienteNotFoundException(idCliente));
    }

    @Override
    public void save(ClienteDTO clienteDTO) {
        log.info("ClienteServiceImpl - save: Guardamos el cliente: " + clienteDTO);

        clienteRepository.save(clienteMapper.toEntity(clienteDTO));
    }

    @Override
    public void delete(Long idCliente) {
        log.info("ClienteServiceImpl - delete: Borramos el cliente: " +
                idCliente);

        clienteRepository.deleteById(idCliente);
    }
}