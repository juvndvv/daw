package edu.juanda.tarea7.beans;

import java.io.Serializable;

public class Alquiler implements Serializable {
    private String nombre;
    private int dias;
    private String edad;
    private String formaPago;
    private String extras;

    public Alquiler() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getEdad() {
        switch (this.edad) {
            case "menos7":
                return "Menor de 7 años";
            case "menos14":
                return "Menor de 14 años";
            case "menos18":
                return "Menor de 18 años";
            case "mayor18":
                return "Mayor de 18 años";
        }
        return "";
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
