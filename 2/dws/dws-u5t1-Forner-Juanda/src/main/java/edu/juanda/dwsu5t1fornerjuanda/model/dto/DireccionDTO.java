package edu.juanda.dwsu5t1fornerjuanda.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
public class DireccionDTO implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private Long idDireccion;
    private String descripcion;
    private String pais;
    private String cp;

    @ToString.Exclude
    private List<ClienteDTO> listaClientesDTO;

    public DireccionDTO() {
        this.listaClientesDTO = new ArrayList<>();
    }
}
