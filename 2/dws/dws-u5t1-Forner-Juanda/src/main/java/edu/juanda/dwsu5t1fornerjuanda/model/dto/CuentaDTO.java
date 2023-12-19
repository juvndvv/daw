package edu.juanda.dwsu5t1fornerjuanda.model.dto;

import lombok.*;

import java.io.Serial;
import java.util.List;

@Data
public class CuentaDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String banco;
    private String sucursal;
    private String numeroCuenta;
    private String dc;
    private double saldo;

    @ToString.Exclude
    private ClienteDTO propietarioDto;

    private List<MovimientoDTO> movimientosDto;
}
