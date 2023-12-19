package edu.juanda.dwsu5t1fornerjuanda.model.dto;

import lombok.*;

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
