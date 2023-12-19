package edu.juanda.dwsu4t4fornerjuanda.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;
import java.util.Objects;

@Data
public class MovimientoDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String tipo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOperacion;
    private String descripcion;
    private double importe;
    @ToString.Exclude private CuentaDTO cuentaOrigen;
    @ToString.Exclude private CuentaDTO cuentaDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimientoDTO that = (MovimientoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
