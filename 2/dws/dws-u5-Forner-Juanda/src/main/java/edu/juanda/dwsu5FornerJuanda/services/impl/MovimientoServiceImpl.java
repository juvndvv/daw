package edu.juanda.dwsu5FornerJuanda.services.impl;

import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.MovimientoDTO;
import edu.juanda.dwsu5FornerJuanda.models.mappers.ClienteMapper;
import edu.juanda.dwsu5FornerJuanda.models.mappers.CuentaMapper;
import edu.juanda.dwsu5FornerJuanda.models.mappers.MovimientoMapper;
import edu.juanda.dwsu5FornerJuanda.repositories.dao.MovimientoRepository;
import edu.juanda.dwsu5FornerJuanda.services.CuentaService;
import edu.juanda.dwsu5FornerJuanda.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    MovimientoMapper movimientoMapper;

    @Autowired
    CuentaMapper cuentaMapper;

    @Autowired
    ClienteMapper clienteMapper;

    @Override
    public List<MovimientoDTO> findByCuenta(Long idCuenta) {
        return movimientoRepository.findMovimientosByCuentaOrigenIdOrCuentaDestinoId(idCuenta, idCuenta)
                .stream()
                .map(movimiento -> movimientoMapper.toDTO(
                        movimiento,
                        cuentaMapper.toDTO(
                                movimiento.getCuentaOrigen(),
                                clienteMapper.toDTO(movimiento.getCuentaOrigen().getCliente())
                        ),
                        cuentaMapper.toDTO(
                                movimiento.getCuentaDestino(),
                                clienteMapper.toDTO(movimiento.getCuentaDestino().getCliente())
                        )
                ))
                .toList();
    }

    @Override
    public MovimientoDTO findById(Long id) {
        return movimientoRepository.findById(id)
                .map(movimiento -> movimientoMapper.toDTO(
                        movimiento,
                        cuentaMapper.toDTO(
                                movimiento.getCuentaOrigen(),
                                clienteMapper.toDTO(movimiento.getCuentaOrigen().getCliente())),
                        cuentaMapper.toDTO(
                                movimiento.getCuentaDestino(),
                                clienteMapper.toDTO(movimiento.getCuentaDestino().getCliente()))
                ))
                .orElse(new MovimientoDTO());
    }

    @Override
    public void save(MovimientoDTO movimientoDTO) {
        // Establece fecha al movimiento
        if (movimientoDTO.getFecha() == null)
            movimientoDTO.setFecha(new Date(System.currentTimeMillis()));

        // Establece la cuenta de origen
        CuentaDTO cuentaOrigenDTO = cuentaService.findById(movimientoDTO.getCuentaOrigenDTO().getId());
        movimientoDTO.setCuentaOrigenDTO(cuentaOrigenDTO);

        // Establece cuenta destino
        CuentaDTO cuentaDestinoDTO = cuentaService.findById(movimientoDTO.getCuentaDestinoDTO().getId());
        movimientoDTO.setCuentaDestinoDTO(cuentaDestinoDTO);

        // Si se estaba editando un movimiento deshacer la transaccion
        if (movimientoDTO.getId() != null) {
            deshacerTransaccion(this.findById(movimientoDTO.getId()), cuentaOrigenDTO, cuentaDestinoDTO);
        }

        // Guarda el movimiento
        movimientoRepository.save(
                movimientoMapper.toEntity(
                        movimientoDTO,
                        cuentaMapper.toEntity(
                                movimientoDTO.getCuentaOrigenDTO(),
                                clienteMapper.toEntity(movimientoDTO.getCuentaOrigenDTO().getClienteDTO())
                        ),
                        cuentaMapper.toEntity(
                                movimientoDTO.getCuentaDestinoDTO(),
                                clienteMapper.toEntity(movimientoDTO.getCuentaDestinoDTO().getClienteDTO())
                        )
                )
        );

        hacerTransaccion(movimientoDTO, cuentaOrigenDTO, cuentaDestinoDTO);
    }

    @Override
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }

    private void hacerTransaccion(MovimientoDTO movimientoDTO, CuentaDTO cuentaOrigenDTO, CuentaDTO cuentaDestinoDTO) {
        cuentaOrigenDTO.setSaldo(cuentaOrigenDTO.getSaldo() - movimientoDTO.getImporte());
        cuentaService.save(cuentaOrigenDTO);

        cuentaDestinoDTO.setSaldo(cuentaDestinoDTO.getSaldo() + movimientoDTO.getImporte());
        cuentaService.save(cuentaDestinoDTO);
    }

    private void deshacerTransaccion(MovimientoDTO movimientoDTO, CuentaDTO cuentaOrigenDTO, CuentaDTO cuentaDestinoDTO) {
        hacerTransaccion(movimientoDTO, cuentaDestinoDTO, cuentaOrigenDTO);
    }
}
