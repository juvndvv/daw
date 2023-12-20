package edu.juanda.dwsu5FornerJuanda.models.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;

@Data
public class RecomendacionDTO implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String observaciones;

    @ToString.Exclude
    private ClienteDTO clienteDTO;
}