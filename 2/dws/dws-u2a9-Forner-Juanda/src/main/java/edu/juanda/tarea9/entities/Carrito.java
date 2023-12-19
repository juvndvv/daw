package edu.juanda.tarea9.entities;

import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Hashtable;

public class Carrito implements Serializable {
    public Hashtable<Integer, Integer> carrito;

    public Carrito() {
        carrito = new Hashtable<>();
    }

    public Hashtable<Integer, Integer> getCarrito() {
        return carrito;
    }

    public void add(int id) {
        // Verificar si el ID ya está en el mapa
        if (carrito.containsKey(id)) {
            // Si sí, obtener la cantidad actual y aumentarla en 1
            int cantidadActual = carrito.get(id);
            carrito.put(id, cantidadActual + 1);
        } else {
            // Si no, agregar el ID al mapa con cantidad 1
            carrito.put(id, 1);
        }
    }
}
