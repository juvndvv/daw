package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;

import java.io.Serial;

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
    private ClienteDTO clienteDTO;
}
