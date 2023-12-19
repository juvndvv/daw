package edu.juanda.dwsu4t3fornerjuanda.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
public class Cuenta {
    private Long id;
    private String banco;
    private String sucursal;
    private String numeroCuenta;
    private String dc;
    private double saldo;
    @ToString.Exclude private Cliente propietario;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuenta other = (Cuenta) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
