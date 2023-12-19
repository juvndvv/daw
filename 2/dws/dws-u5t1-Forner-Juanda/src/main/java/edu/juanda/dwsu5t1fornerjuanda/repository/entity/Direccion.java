package edu.juanda.dwsu5t1fornerjuanda.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    private String descripcion;
    private String pais;
    private String cp;

    @ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "listaDirecciones")
    @ToString.Exclude
    private List<Cliente> listaClientes;

    public Direccion() {
        this.listaClientes = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(idDireccion, direccion.idDireccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDireccion );
    }
}
