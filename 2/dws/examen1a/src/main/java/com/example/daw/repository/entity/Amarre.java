package com.example.daw.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Amarre {
    private Long id;
    private int numero;
    private String tipo;
    private String dimensiones;
    @ToString.Exclude Zona zona;
    List<Barco> listaBarcos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amarre amarre = (Amarre) o;
        return Objects.equals(id, amarre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
