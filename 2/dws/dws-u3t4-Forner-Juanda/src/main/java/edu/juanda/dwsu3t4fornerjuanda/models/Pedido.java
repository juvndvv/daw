package edu.juanda.dwsu3t4fornerjuanda.models;

import lombok.Data;

import java.util.Arrays;

@Data
public class Pedido {
    private String nombre;
    private String direccion;
    private String[] ingredientes;
    private String tamano;
    private String formaPago;
    private String instrucciones;

    @Override
    public String toString() {
        return "Pedido{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ingredientes=" + Arrays.toString(ingredientes) +
                ", tamano='" + tamano + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                '}';
    }
}
