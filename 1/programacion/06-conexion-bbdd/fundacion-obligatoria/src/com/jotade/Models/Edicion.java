package com.jotade.Models;

import java.sql.Date;

public class Edicion {
    protected String isbn;
    protected Date fechaPublicacion;

    public Edicion() {
        this.isbn = "";
        this.fechaPublicacion = new Date(0);
    }

    public Edicion(String isbn, Date fechaPublicacion) {
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String toString() {
        return String.format("isbn: %s, fecha_publicacion: %s", isbn, fechaPublicacion.toString());
    }
}