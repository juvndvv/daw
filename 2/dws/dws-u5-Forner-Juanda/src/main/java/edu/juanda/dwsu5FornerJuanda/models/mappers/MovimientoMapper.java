package edu.juanda.dwsu5FornerJuanda.models.mappers;

import edu.juanda.dwsu5FornerJuanda.models.dto.CuentaDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.MovimientoDTO;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Cuenta;
import edu.juanda.dwsu5FornerJuanda.repositories.entities.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public MovimientoDTO toDTO(Movimiento movimiento,
                               CuentaDTO cuentaOrigenDTO,
                               CuentaDTO cuentaDestinoDTO) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(movimiento.getId());
        movimientoDTO.setTipo(movimiento.getTipo());
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setDescripcion(movimiento.getDescripcion());
        movimientoDTO.setImporte(movimiento.getImporte());
        movimientoDTO.setCuentaOrigenDTO(cuentaOrigenDTO);
        movimientoDTO.setCuentaDestinoDTO(cuentaDestinoDTO);

        return movimientoDTO;
    }

    public Movimiento toEntity(MovimientoDTO movimientoDTO,
                               Cuenta cuentaorigendto,
                               Cuenta cuentaDestino) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(movimientoDTO.getId());
        movimiento.setTipo(movimientoDTO.getTipo());
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setDescripcion(movimientoDTO.getDescripcion());
        movimiento.setImporte(movimientoDTO.getImporte());
        movimiento.setCuentaOrigen(cuentaorigendto);
        movimiento.setCuentaDestino(cuentaDestino);

        return movimiento;
    }
}
