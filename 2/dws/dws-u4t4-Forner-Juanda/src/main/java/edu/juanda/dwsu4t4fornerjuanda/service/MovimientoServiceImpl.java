package edu.juanda.dwsu4t4fornerjuanda.service;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.ClienteDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.mapper.CuentaMapper;
import edu.juanda.dwsu4t4fornerjuanda.model.mapper.MovimientoMapper;
import edu.juanda.dwsu4t4fornerjuanda.repository.dao.MovimientoRepository;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Movimiento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaMapper cuentaMapper;

    @Autowired
    MovimientoMapper movimientoMapper;

    @Override
    public List<MovimientoDTO> findAll() {
        return movimientoRepository.findAll()
                .stream()
                .map(movimiento -> movimientoMapper.convertToDTO(
                        movimiento,
                        cuentaMapper.convertToDTO(movimiento.getCuentaOrigen(), new ClienteDTO()),
                        cuentaMapper.convertToDTO(movimiento.getCuentaDestino(), new ClienteDTO())))
                .toList();
    }

    @Override
    public MovimientoDTO findById(MovimientoDTO movimientoDTO) {
        log.info("MovimientoServiceImpl - findById: Buscar movimiento por id: " + movimientoDTO.getId());

        // Mapea el DTO a entity
        Movimiento movimiento = movimientoMapper.convertToEntity(movimientoDTO, new Cuenta(), new Cuenta());

        // Obtiene el movimiento
        movimiento = movimientoRepository.findById(movimiento);

        // Mapea las cuentas de origen y destino a DTO
        CuentaDTO cuentaOrigenDTO = cuentaMapper.convertToDTO(movimiento.getCuentaOrigen(), new ClienteDTO());
        CuentaDTO cuentaDestinoDTO = cuentaMapper.convertToDTO(movimiento.getCuentaDestino(), new ClienteDTO());

        // Devuelve el movimiento mapeado a DTO
        return movimientoMapper.convertToDTO(movimiento, cuentaOrigenDTO, cuentaDestinoDTO);
    }

    @Override
    public List<MovimientoDTO> findByCuenta(CuentaDTO cuentaDTO) {
        log.info("MovimientoServiceImpl - findByCuenta: Buscar movimientos por cuenta con el id: "
                + cuentaDTO.getId());

        // Mapea el DTO a Entity
        Cuenta cuenta = cuentaMapper.convertToEntity(cuentaDTO, new Cliente());

        // Obtiene los movimientos
        List<Movimiento> movimientos = movimientoRepository.findByIdCuenta(cuenta);

        // Mapea cada movimiento a DTO y los devuelve
        return movimientos
                .stream()
                .map(movimiento -> movimientoMapper.convertToDTO(
                        movimiento,
                        cuentaMapper.convertToDTO(movimiento.getCuentaOrigen(), new ClienteDTO()),
                        cuentaMapper.convertToDTO(movimiento.getCuentaDestino(), new ClienteDTO())))
                .toList();
    }

    @Override
    public List<String> save(MovimientoDTO movimientoDTO) {
        log.info("MovimientoServiceImpl - save: Validando el movimiento: " + movimientoDTO.getId());

        List<String> errores = validarMovimientoDTO(movimientoDTO);

        if (errores.isEmpty()) {
            log.info("MovimientoServiceImpl - save: Salvamos el movimiento");

            // Si la fecha no esta se establece
            if (movimientoDTO.getFechaOperacion() == null)
                movimientoDTO.setFechaOperacion(new Date(System.currentTimeMillis()));

            // Mapea a Entity y la guarda en el repo
            Cuenta cuentaOrigen = cuentaMapper.convertToEntity(
                    movimientoDTO.getCuentaOrigen(),
                    new Cliente()
            );

            Cuenta cuentaDestino = cuentaMapper.convertToEntity(
                    movimientoDTO.getCuentaDestino(),
                    new Cliente()
            );

            Movimiento movimiento = movimientoMapper.convertToEntity(movimientoDTO, cuentaOrigen, cuentaDestino);
            movimientoRepository.save(movimiento);

        } else {
            log.warn("MovimientoServiceImpl - save: Hubieron errores de validacion");
        }

        return errores;
    }

    @Override
    public void delete(MovimientoDTO movimientoDTO) {
        log.info("MovimientoServiceImpl - delete: Borramos el movimiento: " + movimientoDTO);

        // Mapea a Enitity y la guarda en el repo
        Cuenta cuentaOrigen = cuentaMapper.convertToEntity(movimientoDTO.getCuentaOrigen(), new Cliente());
        Cuenta cuentaDestino = cuentaMapper.convertToEntity(movimientoDTO.getCuentaDestino(), new Cliente());

        Movimiento movimiento = movimientoMapper.convertToEntity(movimientoDTO, cuentaOrigen, cuentaDestino);
        movimientoRepository.delete(movimiento);
    }

    public static List<String> validarMovimientoDTO(MovimientoDTO movimientoDTO) {
        return new ArrayList<>();
    }
}
