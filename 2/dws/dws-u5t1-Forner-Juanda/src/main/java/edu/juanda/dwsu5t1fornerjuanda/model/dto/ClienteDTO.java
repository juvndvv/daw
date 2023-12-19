package edu.juanda.dwsu5t1fornerjuanda.model.dto;

import lombok.Data;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

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
    private RecomendacionDTO recomendacionDto;
    private List<CuentaDTO> cuentasDto;
    private List<DireccionDTO> direccionesDTO;

    public ClienteDTO() {
        this.recomendacionDto = new RecomendacionDTO();
        this.direccionesDTO = new ArrayList<>();
    }
}
