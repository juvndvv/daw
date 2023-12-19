package com.example.daw.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
public class Barco {
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private int anoConstruccion;
    private int orden;
    @ToString.Exclude private Amarre amarre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barco barco = (Barco) o;
        return Objects.equals(id, barco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
