package edu.juanda.dwsu4t3fornerjuanda.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    public RecomendacionDTO recomendacionDTO;
    public List<CuentaDTO> cuentas;

    // Constructor vacio
    public ClienteDTO() {
        super();
        this.recomendacionDTO = new RecomendacionDTO();
        cuentas = new ArrayList<>();
    }
}
