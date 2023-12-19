package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.exceptions.ClienteNotFoundException;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.DireccionDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.DireccionMapper;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.ClienteRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.DireccionRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Direccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {
    private static final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DireccionMapper direccionMapper;

    @Override
    public List<DireccionDTO> findAll() {
        log.info("DireccionServiceImpl - findAll: Lista de todos los clientes");

        List<Direccion> direcciones = direccionRepository.findAll();

        return direcciones
                .stream()
                .map(direccion -> direccionMapper.toDto(direccion, new ClienteDTO()))
                .toList();
    }

    @Override
    public List<DireccionDTO> findAllByCliente(Long idCliente) {
        log.info("DireccionServiceImpl - findAllByCliente: Lista de todas las direcciones del cliente");

        List<Direccion> direcciones = direccionRepository.findAllByCliente(idCliente);

        return direcciones
                .stream()
                .map(direccion -> direccionMapper.toDto(direccion, new ClienteDTO()))
                .toList();
    }

    @Override
    public void save(DireccionDTO direccionDTO) {
        log.info("DireccionServiceImpl - save: Salva la direccion del cliente: " +
                direccionDTO.getListaClientesDTO().get(0).getId());

        Optional<Cliente> cliente = clienteRepository.findById(direccionDTO.getListaClientesDTO().get(0).getId());

        if (cliente.isPresent()) {
            Direccion direccion = direccionMapper.toEntity(direccionDTO, cliente.get());
            cliente.get().getListaDirecciones().add(direccion);
            direccionRepository.save(direccion);
        }
    }
}
