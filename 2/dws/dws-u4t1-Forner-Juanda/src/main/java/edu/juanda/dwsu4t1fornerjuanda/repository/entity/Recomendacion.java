package edu.juanda.dwsu4t1fornerjuanda.repository.entity;

import lombok.Data;
import lombok.ToString;

@Data
public class Recomendacion {
    private Long id;
    private String observaciones;
    @ToString.Exclude private Cliente cliente;
}
