package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.exceptions.CuentaNotFoundException;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.ClienteMapper;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.CuentaMapper;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.CuentaRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<CuentaDTO> findAll() {
        return cuentaRepository.findAll().stream()
                .map(cuenta -> cuentaMapper.toDto(
                        cuenta,
                        clienteMapper.toDto(cuenta.getPropietario())
                ))
                .toList();
    }

    @Override
    public CuentaDTO findById(Long idCuenta) {
        return cuentaRepository.findById(idCuenta)
                .map(cuenta -> cuentaMapper.toDto(
                        cuenta,
                        clienteMapper.toDto(cuenta.getPropietario())
                ))
                .orElseThrow(() -> new CuentaNotFoundException(idCuenta));
    }

    @Override
    public List<CuentaDTO> findByPropietario(Long idPropietario) {
        // Comprobamos que el cliente exista
        clienteService.findById(idPropietario);

        // Buscamos las cuentas del cliente
        return cuentaRepository.findCuentaByPropietarioIdCliente(idPropietario).stream()
                .map(cuenta -> cuentaMapper.toDto(
                        cuenta,
                        clienteMapper.toDto(cuenta.getPropietario())
                ))
                .toList();
    }

    @Override
    public void save(CuentaDTO cuentaDto) {
        Cliente cliente = clienteMapper.toEntity(cuentaDto.getPropietarioDto());
        cuentaRepository.save(cuentaMapper.toEntity(cuentaDto, cliente));
    }

    @Override
    public void delete(Long idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }
}
