package com.example.daw.model.dto;

import com.example.daw.repository.entity.Amarre;
import com.example.daw.repository.entity.Barco;
import lombok.Data;
import lombok.ToString;;import java.util.Objects;

@Data
public class BarcoDTO {
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private int anoConstruccion;
    private int orden;
    @ToString.Exclude private AmarreDTO amarreDTO;

    public static BarcoDTO toDTO (Barco barco, AmarreDTO amarreDTO) {
        BarcoDTO barcoDTO = new BarcoDTO();
        barcoDTO.setId(barco.getId());
        barcoDTO.setMatricula(barco.getMatricula());
        barcoDTO.setMarca(barco.getMarca());
        barcoDTO.setModelo(barco.getModelo());
        barcoDTO.setAnoConstruccion(barco.getAnoConstruccion());
        barcoDTO.setOrden(barcoDTO.getOrden());
        barcoDTO.setAmarreDTO(amarreDTO);

        return barcoDTO;
    }

    public static Barco toEntity(BarcoDTO barcoDTO, Amarre amarre) {
        Barco barco = new Barco();
        barco.setId(barcoDTO.getId());
        barco.setMatricula(barcoDTO.getMatricula());
        barco.setMarca(barcoDTO.getMarca());
        barco.setModelo(barcoDTO.getModelo());
        barco.setAnoConstruccion(barcoDTO.getAnoConstruccion());
        barco.setOrden(barco.getOrden());
        barco.setAmarre(amarre);

        return barco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarcoDTO barcoDTO = (BarcoDTO) o;
        return Objects.equals(id, barcoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
