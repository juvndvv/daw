package com.example.daw.model.dto;

import com.example.daw.repository.entity.Zona;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ZonaDTO implements Serializable {
    private Long id;
    private String letra;
    private int profundidad;
    private String dimensiones;
    private List<AmarreDTO> listaAmarresDTO = new ArrayList<>();

    public static ZonaDTO toDTO(Zona zona) {
        ZonaDTO zonaDTO = new ZonaDTO();
        zonaDTO.setId(zona.getId());
        zonaDTO.setLetra(zona.getLetra());
        zonaDTO.setProfundidad(zona.getProfundidad());
        zonaDTO.setDimensiones(zona.getDimensiones());

        return zonaDTO;
    }

    public static Zona toEntity(ZonaDTO zonaDTO) {
        Zona zona = new Zona();
        zona.setId(zonaDTO.getId());
        zona.setLetra(zonaDTO.getLetra());
        zona.setProfundidad(zonaDTO.getProfundidad());
        zona.setDimensiones(zonaDTO.getDimensiones());

        return zona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZonaDTO zonaDTO = (ZonaDTO) o;
        return Objects.equals(id, zonaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
