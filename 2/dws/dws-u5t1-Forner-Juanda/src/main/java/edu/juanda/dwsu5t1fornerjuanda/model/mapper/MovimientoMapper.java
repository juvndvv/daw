package edu.juanda.dwsu5t1fornerjuanda.model.mapper;

import edu.juanda.dwsu5t1fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu5t1fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu5t1fornerjuanda.repository.entity.Movimiento;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MovimientoMapper {

    public MovimientoDTO toDto(Movimiento movimiento, CuentaDTO cuentaOrigenDTO, CuentaDTO cuentaDestinoDTO) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(movimiento.getIdMovimiento());
        movimientoDTO.setTipo(movimiento.getTipo());
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setDescripcion(movimiento.getDescripcion());
        movimientoDTO.setImporte(movimiento.getImporte());
        movimientoDTO.setCuentaOrigenDto(cuentaOrigenDTO);
        movimientoDTO.setCuentaDestinoDto(cuentaDestinoDTO);
        return movimientoDTO;
    }

    public Movimiento toEntity(MovimientoDTO movimientoDTO, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(movimientoDTO.getId());
        movimiento.setTipo(movimientoDTO.getTipo());
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setDescripcion(movimientoDTO.getDescripcion());
        movimiento.setImporte(movimientoDTO.getImporte());
        movimiento.setCuentaOrigen(cuentaOrigen);
        movimiento.setCuentaDestino(cuentaDestino);
        return movimiento;
    }
}
