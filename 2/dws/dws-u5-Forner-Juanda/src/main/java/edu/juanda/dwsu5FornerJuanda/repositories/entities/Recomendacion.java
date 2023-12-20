package edu.juanda.dwsu5FornerJuanda.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@Entity
@Table(name = "recomendaciones")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String observaciones;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente")
    @ToString.Exclude
    private Cliente cliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recomendacion that = (Recomendacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}