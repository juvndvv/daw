package edu.juanda.tarea9.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Database {
    private ArrayList<Producto> db;

    public Database() {
        this.db = new ArrayList<>(Arrays.asList(
                new Producto(1, "Air Force 1", 125.25f),
                new Producto(2, "Adidas Superstar", 150.55f),
                new Producto(3, "Neke Delmer", 2.5f),
                new Producto(4, "Chanclas", 8.55f),
                new Producto(5, "Tacones", 999)
        ));
    }

    public String getNombre(int id) {
        return db.stream()
                .filter(producto -> producto.getId() == id)
                .collect(Collectors.toList())
                .get(0)
                .getNombre();
    }

    public float getPrecio(int id){
        return db.stream()
                .filter(producto -> producto.getId() == id)
                .collect(Collectors.toList())
                .get(0)
                .getPrecio();
    }
}
