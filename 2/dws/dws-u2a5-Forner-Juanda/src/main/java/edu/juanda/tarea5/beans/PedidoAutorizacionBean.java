package edu.juanda.tarea5.beans;

import java.io.Serializable;
import java.util.Random;

public class PedidoAutorizacionBean implements Serializable {
    private String prueba;
    private int cantidad;
    private String estado;
    private String motivo;

    public PedidoAutorizacionBean() {
        // Generar aleatoriamente el estado y el motivo
        String[] estados = {"Aceptado", "Denegado"};
        this.estado = estados[new Random().nextInt(estados.length)];

        if (this.estado.equalsIgnoreCase("Denegado")) {
            this.motivo = "No tienes los permisos suficientes";

        } else {
            this.motivo = "";
        }
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
