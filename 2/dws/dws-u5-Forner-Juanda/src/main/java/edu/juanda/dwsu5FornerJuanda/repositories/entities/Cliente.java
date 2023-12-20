package edu.juanda.dwsu5FornerJuanda.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Cuenta> cuentas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "clientes_direcciones",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direccion")
    )
    @ToString.Exclude
    private Set<Direccion> direcciones;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
    @ToString.Exclude
    private Set<Pedido> pedidos;

    public Cliente() {
        super();
        this.recomendacion = new Recomendacion();
        this.cuentas = new HashSet<>();
        this.direcciones = new HashSet<>();
        this.pedidos = new HashSet<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
