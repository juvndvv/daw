package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;

import java.io.Serial;

@Data
public class ProveedorDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String cif;
}
