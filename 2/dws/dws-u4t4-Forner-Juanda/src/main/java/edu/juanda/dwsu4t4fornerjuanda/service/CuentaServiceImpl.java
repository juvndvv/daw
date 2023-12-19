package edu.juanda.dwsu4t4fornerjuanda.service;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;

import edu.juanda.dwsu4t4fornerjuanda.model.mapper.ClienteMapper;
import edu.juanda.dwsu4t4fornerjuanda.model.mapper.CuentaMapper;
import edu.juanda.dwsu4t4fornerjuanda.repository.dao.ClienteRepository;
import edu.juanda.dwsu4t4fornerjuanda.repository.dao.CuentaRepository;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {
    private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    ClienteMapper clienteMapper;

    @Autowired
    CuentaMapper cuentaMapper;

    @Override
    public List<CuentaDTO> findAll() {
        return cuentaRepository.findAll()
                .stream()
                .map(cuenta -> cuentaMapper.convertToDTO(
                        cuenta,
                        clienteMapper.convertToDTO(cuenta.getPropietario())
                ))
                .toList();
    }

    @Override
    public CuentaDTO findById(CuentaDTO cuentaDTO) {
        log.info("CuentaServiceImpl - findById: Buscar cuenta por id: " + cuentaDTO.getId());

        // Mapea la cuentaDTO a cuenta
        Cuenta cuenta = cuentaMapper.convertToEntity(cuentaDTO, new Cliente());

        // Obtiene la cuenta
        cuenta = cuentaRepository.findById(cuenta);

        // Mapea el propietario a DTO
        ClienteDTO clienteDTO = clienteMapper.convertToDTO(cuenta.getPropietario());

        // Devuelve la cuenta mapeada a DTO
        return cuentaMapper.convertToDTO(cuenta, clienteDTO);
    }

    @Override
    public List<CuentaDTO> findByClient(ClienteDTO clienteDTO) {
        log.info("CuentaServiceImpl - findByClient: Buscar cuenta por cliente con id: " + clienteDTO.getId());

        // Mapea el clienteDTO a cliente
        Cliente cliente = clienteMapper.convertToEntity(clienteDTO);

        // Obtiene las cuentas
        List<Cuenta> cuentas = cuentaRepository.findByIdCliente(cliente);

        // Obtiene los detalles del DTO
        ClienteDTO detailedClienteDTO = clienteMapper.convertToDTO(clienteRepository.findById(clienteMapper.convertToEntity(clienteDTO)));
        clienteDTO.setAll(detailedClienteDTO);

        // Mapea las cuentas a DTO
        List<CuentaDTO> cuentasDTO = new ArrayList<>();
        for (Cuenta cuenta: cuentas) {
            cuentasDTO.add(cuentaMapper.convertToDTO(cuenta, clienteDTO));
        }

        return cuentasDTO;
    }

    @Override
    public List<String> save(CuentaDTO cuentaDTO) {
        log.info("CuentaServiceImpl - save: Validando la cuenta: " + cuentaDTO);
        List<String> errores = validarCuentaDTO(cuentaDTO);

        if (errores.isEmpty()) {
            log.info("CuentaServiceImpl - save: Salvamos la cuenta");

            // Mapea la cuenta y la guarda en el repo
            Cliente cliente = clienteMapper.convertToEntity(cuentaDTO.getPropietario());
            cuentaRepository.save(cuentaMapper.convertToEntity(cuentaDTO, cliente));

        } else {
            log.warn("CuentaServiceImpl - save: Hubieron errores de validación");
        }

        return errores;
    }

    @Override
    public void delete(CuentaDTO cuentaDTO) {
        log.info("ClienteServiceImpl - delete: Borramos la cuenta: " + cuentaDTO);

        Cuenta cuenta = cuentaMapper.convertToEntity(cuentaDTO, new Cliente());
        cuentaRepository.delete(cuenta);
    }

    public List<String> validarCuentaDTO(CuentaDTO cuentaDTO) {
        return new ArrayList<>();
    }
}
