package com.jotade.Models;

import java.sql.Date;

public class Libro extends Edicion {
    private String dniAutor;
    private String titulo;
    private String dniSocio;

    public Libro() {
        super();
        this.dniAutor = "";
        this.titulo = "";
        this.dniSocio = "";
    }

    public Libro(String isbn, String dniAutor, String titulo, String dniSocio) {
        super(isbn, new Date(System.currentTimeMillis()));
        this.dniAutor = dniAutor;
        this.titulo = titulo;
        this.dniSocio = dniSocio;
    }

    public void setDniAutor(String dniAutor) {
        this.dniAutor = dniAutor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public String getDniAutor() {
        return dniAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public String toString() {
        return String.format("isbn: %s, fecha_publicacion: %s, dni_autor: %s, titulo: %s, dni_socio: %s",
                isbn, fechaPublicacion.toString(), dniAutor, titulo, dniSocio);
    }
}
