package edu.juanda.dwsu5t1fornerjuanda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    private String banco;

    private String sucursal;

    private String numeroCuenta;

    private String dc;

    private double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    @ToString.Exclude
    private Cliente propietario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Movimiento> movimientosOrigen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Movimiento> movimientosDestino;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuenta other = (Cuenta) obj;
        return Objects.equals(idCuenta, other.idCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCuenta);
    }
}
