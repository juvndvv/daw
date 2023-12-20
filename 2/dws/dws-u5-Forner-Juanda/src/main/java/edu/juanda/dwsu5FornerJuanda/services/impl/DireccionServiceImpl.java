package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.DireccionDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.DireccionMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.ClienteRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.DireccionRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Direccion;
import edu.juanda.dwsu5FornerJuanda.services.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private DireccionMapper direccionMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<DireccionDTO> findAll() {
        return direccionRepository.findAll()
                .stream()
                .map(direccion -> direccionMapper.toDTO(direccion, new ClienteDTO()))
                .toList();
    }

    @Override
    public List<DireccionDTO> findAllByCliente(Long idCliente) {
        return direccionRepository.findAllByCliente(idCliente)
                .stream()
                .map(direccion -> direccionMapper.toDTO(direccion, new ClienteDTO()))
                .toList();
    }

    @Override
    public Boolean existsById(Long id) {
        if (id == null)
            return false;

        return direccionRepository.existsById(id);
    }

    @Override
    public void save(DireccionDTO direccionDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(direccionDTO.getClientesDTO().get(0).getId());

        if (cliente.isPresent()) {
            Direccion direccion;

            if (existsById(direccionDTO.getId()))
                direccion = direccionRepository.findById(direccionDTO.getId()).orElse(new Direccion());
            else
                direccion = direccionMapper.toEntity(direccionDTO, cliente.get());

            cliente.get().getDirecciones().add(direccion);
            direccionRepository.save(direccion);
        }
    }
}
