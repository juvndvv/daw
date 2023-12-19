package com.example.daw.model.dto;


import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Zona;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Data
public class AmarreDTO implements Serializable {
    private Long id;
    private int numero;
    private String tipo;
    private String dimensiones;
    @ToString.Exclude ZonaDTO zonaDTO;
    private List<BarcoDTO> listaBarcosDTO = new ArrayList<>();

    public static AmarreDTO toDTO(Amarre amarre, ZonaDTO zonaDTO) {
        AmarreDTO amarreDTO = new AmarreDTO();
        amarreDTO.setId(amarre.getId());
        amarreDTO.setNumero(amarre.getNumero());
        amarreDTO.setTipo(amarre.getTipo());
        amarreDTO.setDimensiones(amarre.getDimensiones());
        amarreDTO.setZonaDTO(zonaDTO);

        return amarreDTO;
    }

    public static Amarre toEntity(AmarreDTO amarreDTO, Zona zona) {
        Amarre amarre = new Amarre();
        amarre.setId(amarreDTO.getId());
        amarre.setNumero(amarreDTO.getNumero());
        amarre.setTipo(amarreDTO.getTipo());
        amarre.setDimensiones(amarreDTO.getDimensiones());
        amarre.setZona(zona);

        return amarre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmarreDTO amarreDTO = (AmarreDTO) o;
        return Objects.equals(id, amarreDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
