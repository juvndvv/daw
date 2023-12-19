package edu.juanda.dwsu5t1fornerjuanda.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;

@Data
public class MovimientoDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String tipo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private String descripcion;
    private double importe;

    @ToString.Exclude
    private CuentaDTO cuentaOrigenDto;

    @ToString.Exclude
    private CuentaDTO cuentaDestinoDto;
}
