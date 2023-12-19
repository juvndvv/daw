package com.jotade.Models;

import java.sql.Date;

public class Revista extends Edicion {
    private String nombre;

    public Revista() {
        super();
        this.nombre = "";
    }

    public Revista(String isbn, String nombre) {
        super(isbn, new Date(System.currentTimeMillis()));
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return String.format("isbn: %s, fecha_publicacion: %s, nombre: %s",
                isbn, fechaPublicacion.toString(), nombre);
    }

    public VentaRevista sell(Persona cliente, int ejemplares) {
        return new VentaRevista(isbn, cliente.getDni(), ejemplares);
    }
}