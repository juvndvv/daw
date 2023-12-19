package com.jotade.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Persona {
    public static final String SOCIO = "socio";
    public static final String CLIENTE = "cliente";
    public static final String AUTOR = "autor";

    public static final ArrayList<String> TIPOS = new ArrayList<>(Arrays.asList(SOCIO, CLIENTE, AUTOR));

    private String dni;
    private String nombre;
    private String telefono;
    private String tipo;

    public Persona() {
        this.dni = "";
        this.nombre = "";
        this.telefono = "";
        this.tipo = "";
    }

    public Persona(String dni, String nombre, String telefono, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getInfo() {
        return String.format("dni: %s, nombre: %s, tipo: %s",
                dni, nombre, telefono);
    }

    public String toString() {
        return String.format("dni: %s, nombre: %s, telefono: %s, tipo: %s",
                dni, nombre, telefono, tipo);
    }
}