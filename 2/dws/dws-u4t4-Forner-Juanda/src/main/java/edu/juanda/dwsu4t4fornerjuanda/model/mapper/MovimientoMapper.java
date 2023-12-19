package edu.juanda.dwsu4t4fornerjuanda.model.mapper;

import edu.juanda.dwsu4t4fornerjuanda.model.dto.CuentaDTO;
import edu.juanda.dwsu4t4fornerjuanda.model.dto.MovimientoDTO;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Cuenta;
import edu.juanda.dwsu4t4fornerjuanda.repository.entity.Movimiento;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MovimientoMapper {
    public MovimientoDTO convertToDTO(Movimiento movimiento, CuentaDTO cuentaOrigenDTO, CuentaDTO cuentaDestinoDTO) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();

        movimientoDTO.setId(movimiento.getId());
        movimientoDTO.setTipo(movimiento.getTipo());
        movimientoDTO.setFechaOperacion(movimiento.getFechaOperacion());
        movimientoDTO.setDescripcion(movimiento.getDescripcion());
        movimientoDTO.setImporte(movimiento.getImporte());
        movimientoDTO.setCuentaOrigen(cuentaOrigenDTO);
        movimientoDTO.setCuentaDestino(cuentaDestinoDTO);

        return movimientoDTO;
    }

    public Movimiento convertToEntity(MovimientoDTO movimientoDTO, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        Movimiento movimiento = new Movimiento();

        movimiento.setId(movimientoDTO.getId());
        movimiento.setTipo(movimientoDTO.getTipo());
        movimiento.setFechaOperacion(movimientoDTO.getFechaOperacion());
        movimiento.setDescripcion(movimientoDTO.getDescripcion());
        movimiento.setImporte(movimientoDTO.getImporte());
        movimiento.setCuentaOrigen(cuentaOrigen);
        movimiento.setCuentaDestino(cuentaDestino);

        return movimiento;
    }
}
