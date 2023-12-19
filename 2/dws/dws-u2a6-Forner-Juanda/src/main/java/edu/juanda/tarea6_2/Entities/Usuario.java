package edu.juanda.tarea6_2.Entities;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String password;

    public Usuario() {

    }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Usuario other) {
        return this.nombre.equals(other.getNombre()) && this.password.equals(other.getPassword());
    }
}
