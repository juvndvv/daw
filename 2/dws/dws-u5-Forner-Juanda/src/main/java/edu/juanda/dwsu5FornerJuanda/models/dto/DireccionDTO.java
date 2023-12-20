package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DireccionDTO implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String descripcion;
    private String pais;
    private String cp;

    @ToString.Exclude
    private List<ClienteDTO> clientesDTO;

    public DireccionDTO() {
        this.clientesDTO = new ArrayList<>();
    }
}
