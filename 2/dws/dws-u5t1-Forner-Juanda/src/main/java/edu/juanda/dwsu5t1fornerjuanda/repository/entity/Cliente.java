package edu.juanda.dwsu5t1fornerjuanda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(length = 9)
    private String nif;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellidos;

    @Column(length = 10)
    private String claveSeguridad;

    @Column(length = 100)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cliente")
    @ToString.Exclude
    private Recomendacion recomendacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propietario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Cuenta> cuentas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "clientes_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion")
    )
    @ToString.Exclude
    private List<Direccion> listaDirecciones = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(idCliente, other.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }
}