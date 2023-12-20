package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ClienteDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    private RecomendacionDTO recomendacionDTO;
    private List<DireccionDTO> direccionesDTO;

    public ClienteDTO() {
        this.recomendacionDTO = new RecomendacionDTO();
        this.direccionesDTO = new ArrayList<>();
    }
}
