package com.jotade.Views;

import java.sql.Connection;

/*
 * Casos de uso:
 *      Ediciones: ver todas, insertar, update, borrar
 *      Articulos: ver todas, insertar, update, borrar
 *      Revistas: ver todas, insertar, update, borrar
 *      Libros: ver todas, insertar, update, borrar
 *      Personas: ver todas, ver clientes, ver socios, ver autores, insertar, insertar nuevo tipo en persona, update en todos tipos, borrar
 *      Ventas de revistas: ver todas, ver de cliente, ver de isbn, insertar, update, borrar
 */

public class GlobalMenu extends MenuImpl {
    public GlobalMenu(Connection conn) {
        super(conn);
    }

    public void show() {
        System.out.println("Elige una opcion:");
        System.out.println("\t1. Ediciones");
        System.out.println("\t2. Articulos");
        System.out.println("\t3. Revistas");
        System.out.println("\t4. Libros");
        System.out.println("\t5. Personas");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void parseOption(int option) {
        switch (option) {
            case -1 -> System.out.println("Opcion invalida!");
            case 0 -> System.out.println("Saliendo...");
            case 1 -> new EdicionesMenu(CONN).start();
            case 2 -> new ArticulosMenu(CONN).start();
            case 3 -> new RevistasMenu(CONN).start();
            case 4 -> new LibrosMenu(CONN).start();
            case 5 -> new PersonasMenu(CONN).start();
        }
    }
}
