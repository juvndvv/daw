package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.ClienteMapper;
import edu.juanda.dwsu5FornerJuanda.models.mappers.CuentaMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.ClienteRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.CuentaRepository;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cliente;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cuenta;
import edu.juanda.dwsu5FornerJuanda.services.ClienteService;
import edu.juanda.dwsu5FornerJuanda.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<CuentaDTO> findAll() {
        return cuentaRepository.findAll()
                .stream()
                .map(cuenta -> cuentaMapper.toDTO(
                        cuenta,
                        clienteMapper.toDTO(cuenta.getCliente())
                ))
                .toList();
    }

    @Override
    public List<CuentaDTO> findAllByCliente(Long idCliente) {
        return cuentaRepository.findCuentasByClienteId(idCliente)
                .stream()
                .map(cuenta -> cuentaMapper.toDTO(cuenta, new ClienteDTO()))
                .toList();
    }

    @Override
    public CuentaDTO findById(Long id) {
        return cuentaMapper.toDTO(
                cuentaRepository.findById(id)
                        .orElse(new Cuenta()),
                clienteService.findByCuentaId(id)
        );
    }

    @Override
    public void save(CuentaDTO cuentaDTO) {
        cuentaRepository.save(
                cuentaMapper.toEntity(
                        cuentaDTO,
                        clienteMapper.toEntity(cuentaDTO.getClienteDTO())
                )
        );
    }

    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }
}
