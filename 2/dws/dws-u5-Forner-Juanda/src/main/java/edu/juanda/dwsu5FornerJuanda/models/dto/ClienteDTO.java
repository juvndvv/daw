package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;

import java.io.Serial;

@Data
public class ClienteDTO {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    private RecomendacionDTO recomendacionDTO;

    public ClienteDTO() {
        this.recomendacionDTO = new RecomendacionDTO();
    }
}
