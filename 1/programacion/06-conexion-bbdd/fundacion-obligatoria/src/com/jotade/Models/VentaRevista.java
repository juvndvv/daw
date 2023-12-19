package com.jotade.Models;

public class VentaRevista {
    private String isbn;
    private String dniCliente;
    private int ejemplares;

    public VentaRevista() {
        this.isbn = "";
        this.dniCliente = "";
        this.ejemplares = 0;
    }

    public VentaRevista(String isbn, String dniCliente, int ejemplares) {
        this.isbn = isbn;
        this.dniCliente = dniCliente;
        this.ejemplares = ejemplares;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public String toString() {
        return String.format("isbn: %s, dni_cliente: %s, ejemplares: %d",
                isbn, dniCliente, ejemplares);
    }
}