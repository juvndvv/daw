package edu.juanda.dwsu5FornerJuanda.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String banco;

    private String sucursal;

    private String numeroCuenta;

    private String dc;

    private double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    @ToString.Exclude
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Movimiento> movimientosOrigen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Movimiento> movimientosDestino;

    public Cuenta() {
        super();
        this.movimientosOrigen = new HashSet<>();
        this.movimientosDestino = new HashSet<>();
    }

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