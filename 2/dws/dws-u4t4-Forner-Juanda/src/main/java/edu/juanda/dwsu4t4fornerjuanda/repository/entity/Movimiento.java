package edu.juanda.dwsu4t4fornerjuanda.repository.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Data
public class Movimiento {
    private Long id;
    private String tipo;
    private Date fechaOperacion;
    private String descripcion;
    private double importe;
    @ToString.Exclude private Cuenta cuentaOrigen;
    @ToString.Exclude private Cuenta cuentaDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimiento that = (Movimiento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
