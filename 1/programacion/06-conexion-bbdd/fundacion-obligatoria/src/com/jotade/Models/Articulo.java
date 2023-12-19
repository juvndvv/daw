package com.jotade.Models;

import java.sql.Date;

public class Articulo extends Edicion {
    private String dniAutor;
    private String titulo;
    private String dniCliente;

    public Articulo() {
        super();
        this.dniAutor = "";
        this.titulo = "";
        this.dniCliente = "";
    }

    public Articulo(String isbn, String dniAutor, String titulo, String dniCliente) {
        super(isbn, new Date(System.currentTimeMillis()));
        this.dniAutor = dniAutor;
        this.titulo = titulo;
        this.dniCliente = dniCliente;
    }

    public void setDniAutor(String dniAutor) {
        this.dniAutor = dniAutor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniAutor() {
        return dniAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public String toString() {
        return String.format("isbn: %s, fecha_publicacion: %s, dni_autor: %s, titulo: %s, dni_cliente: %s",
                isbn, fechaPublicacion.toString(), dniAutor, titulo, dniCliente);
    }
}