package edu.juanda.dwsu5FornerJuanda.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String pais;
    private String cp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "direcciones")
    @ToString.Exclude
    private Set<Cliente> clientes;

    public Direccion() {
        this.clientes = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(id, direccion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
