package com.example.daw.repository.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Zona {
    private Long id;
    private String letra;
    private int profundidad;
    private String dimensiones;
    private List<Amarre> listaAmarres = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zona zona = (Zona) o;
        return Objects.equals(id, zona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
