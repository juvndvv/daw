package edu.juanda.dwsu5t1fornerjuanda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "recomendaciones")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecomendacion;
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
        return Objects.equals(idRecomendacion, that.idRecomendacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecomendacion);
    }
}
