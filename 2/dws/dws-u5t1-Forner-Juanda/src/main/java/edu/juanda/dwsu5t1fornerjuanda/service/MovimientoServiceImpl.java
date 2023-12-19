package edu.juanda.dwsu5t1fornerjuanda.service;

import edu.juanda.dwsu5t1fornerjuanda.exceptions.MovimientoNotFoundException;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.ClienteMapper;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.CuentaMapper;
import edu.juanda.dwsu5t1fornerjuanda.model.mapper.MovimientoMapper;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.CuentaRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.dao.MovimientoRepository;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cliente;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Override
    public List<MovimientoDTO> findAll() {
        return movimientoRepository.findAll().stream()
                .map(movimiento -> movimientoMapper.toDto(movimiento,
                        cuentaMapper.toDto(movimiento.getCuentaOrigen(),
                                clienteMapper.toDto(movimiento.getCuentaOrigen().getPropietario())),
                        cuentaMapper.toDto(movimiento.getCuentaDestino(),
                                clienteMapper.toDto(movimiento.getCuentaDestino().getPropietario()))
                ))
                .toList();
    }

    @Override
    public List<MovimientoDTO> findByCuentaOrigenIdOrCuentaDestinoId(Long idCuentaOrigen, Long idCuentaDestino) {
        return movimientoRepository.findMovimientosByCuentaOrigenIdCuentaOrCuentaDestinoIdCuenta(idCuentaOrigen, idCuentaDestino)
                .stream()
                .map(movimiento -> movimientoMapper.toDto(movimiento,
                        cuentaMapper.toDto(movimiento.getCuentaOrigen(),
                                clienteMapper.toDto(movimiento.getCuentaOrigen().getPropietario())),
                        cuentaMapper.toDto(movimiento.getCuentaDestino(),
                                clienteMapper.toDto(movimiento.getCuentaDestino().getPropietario()))
                ))
                .toList();
    }

    @Override
    public MovimientoDTO findById(Long id) {
        return movimientoRepository.findById(id)
                .map(movimiento -> movimientoMapper.toDto(movimiento,
                        cuentaMapper.toDto(movimiento.getCuentaOrigen(),
                                clienteMapper.toDto(movimiento.getCuentaOrigen().getPropietario())),
                        cuentaMapper.toDto(movimiento.getCuentaDestino(),
                                clienteMapper.toDto(movimiento.getCuentaDestino().getPropietario()))
                ))
                .orElseThrow(() -> new MovimientoNotFoundException(id));
    }

    @Override
    public void save(MovimientoDTO movimientoDto) {
        if (movimientoDto.getFecha() == null)
            movimientoDto.setFecha(new Date(System.currentTimeMillis()));

        // Aplicar el movimiento
        movimientoDto.getCuentaOrigenDto().setSaldo(movimientoDto.getCuentaOrigenDto().getSaldo() - movimientoDto.getImporte());
        movimientoDto.getCuentaDestinoDto().setSaldo(movimientoDto.getCuentaDestinoDto().getSaldo() + movimientoDto.getImporte());

        // Guarda la cuenta origen
        Cuenta cuentaOrigenActualizada = cuentaMapper.toEntity(movimientoDto.getCuentaOrigenDto(), new Cliente());
        cuentaRepository.save(cuentaOrigenActualizada);

        // Guarda la cuenta destino
        Cuenta cuentaDestinoActualizada = cuentaMapper.toEntity(movimientoDto.getCuentaDestinoDto(), new Cliente());
        cuentaRepository.save(cuentaDestinoActualizada);

        // Guardar el movimiento
        Cuenta cuentaOrigen = cuentaMapper.toEntity(movimientoDto.getCuentaOrigenDto(), new Cliente());
        Cuenta cuentaDestino = cuentaMapper.toEntity(movimientoDto.getCuentaDestinoDto(), new Cliente());

        movimientoRepository.save(movimientoMapper.toEntity(movimientoDto, cuentaOrigen, cuentaDestino));
    }

    @Override
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }
}
