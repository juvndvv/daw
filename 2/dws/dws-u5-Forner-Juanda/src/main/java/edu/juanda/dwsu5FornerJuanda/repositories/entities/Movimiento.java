package edu.juanda.dwsu5FornerJuanda.repositories.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Date fecha;

    private String descripcion;

    private double importe;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Cuenta cuentaOrigen;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Cuenta cuentaDestino;
}