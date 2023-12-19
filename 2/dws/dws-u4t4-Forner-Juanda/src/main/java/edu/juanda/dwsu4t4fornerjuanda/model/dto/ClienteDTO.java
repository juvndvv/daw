package edu.juanda.dwsu4t4fornerjuanda.model.dto;

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
    public RecomendacionDTO recomendacionDTO;
    public List<CuentaDTO> cuentas;

    // Constructor vacio
    public ClienteDTO() {
        super();
        this.recomendacionDTO = new RecomendacionDTO();
        cuentas = new ArrayList<>();
    }

    public void setAll(ClienteDTO other) {
        this.nif = other.nif;
        this.nombre = other.nombre;
        this.apellidos = other.apellidos;
        this.claveSeguridad = other.claveSeguridad;
        this.email = other.email;
        this.recomendacionDTO = other.recomendacionDTO;
        this.cuentas = other.cuentas;
    }
}
