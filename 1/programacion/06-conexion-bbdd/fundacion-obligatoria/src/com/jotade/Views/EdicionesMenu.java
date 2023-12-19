package com.jotade.Views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.jotade.Controllers.EdicionController;
import com.jotade.Database.ErrorCodes;
import com.jotade.Models.Edicion;

@SuppressWarnings("resource")
public class EdicionesMenu extends MenuImpl {

    public EdicionesMenu(Connection conn) {
        super(conn);
    }

    @Override
    public void parseOption(int option) {
        switch (option) {
            case 1 -> showAll();
            case 2 -> insert();
            case 3 -> update();
            case 4 -> delete();
            case 0 -> System.out.println("Saliendo al menu principal...");
            default -> System.out.println("Opcion invalida");
        }
    }

    @Override
    public void show() {
        System.out.println("[EDICIONES]");
        System.out.println("\t1. Ver todas");
        System.out.println("\t2. Insertar");
        System.out.println("\t3. Actualizar");
        System.out.println("\t4. Borrar");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void showAll() {
        try {
            ArrayList<Edicion> ediciones = EdicionController.getAll(CONN);

            ediciones.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void insert() {
        try {
            Edicion e = create();
            EdicionController.save(e, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IllegalArgumentException e) {
            System.out.println("Has introducido datos no validos al crear la edicion");
        }
    }

    public void update() {
        try {
            Edicion edicion = readEdicion(CONN);
            modify(edicion);
            EdicionController.update(edicion, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }

    }

    public void delete() {
        try {
            Edicion edicion = readEdicion(CONN);
            EdicionController.deleteByIsbn(edicion.getIsbn(), CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }
    }

    public static Edicion create() throws IllegalArgumentException {
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        System.out.print("Introduce la fecha de publicacion (yyyy-[m]m-[d]d): ");
        Date fechaPublicacion = Date.valueOf(reader.nextLine());

        return new Edicion(isbn, fechaPublicacion);

    }

    public static void modify(Edicion edicion) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Introduce la fecha de publicacion (yyyy-[m]m-[d]d): ");
        edicion.setFechaPublicacion(Date.valueOf(reader.nextLine()));
    }

    public static Edicion readEdicion(Connection conn) throws SQLException {
        System.out.println("Ediciones:");
        ArrayList<Edicion> ediciones = EdicionController.getAll(conn);
        ediciones.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return ediciones
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }
}
