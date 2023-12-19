package com.jotade.Views;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.jotade.Controllers.ArticuloController;
import com.jotade.Database.ErrorCodes;
import com.jotade.Models.Articulo;
import com.jotade.Models.Persona;

@SuppressWarnings("resource")
public class ArticulosMenu extends MenuImpl {
    public ArticulosMenu(Connection conn) {
        super(conn);
    }

    @Override
    public void parseOption(int option) {
        switch (option) {
            case 1 -> showAll();
            case 2 -> insert();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> vender();
            case 6 -> devolver();
            case 0 -> System.out.println("Saliendo al menu principal...");
            default -> System.out.println("Opcion invalida");
        }
    }

    @Override
    public void show() {
        System.out.println("[ARTICULOS]");
        System.out.println("\t1. Ver todos");
        System.out.println("\t2. Insertar");
        System.out.println("\t3. Actualizar");
        System.out.println("\t4. Borrar");
        System.out.println("\t5. Vender");
        System.out.println("\t6. Devolver");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void showAll() {
        try {
            ArrayList<Articulo> articulos = ArticuloController.getAll(CONN);
            articulos.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void insert() {
        try {
            Articulo e = create();
            ArticuloController.save(e, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IllegalArgumentException e) {
            System.out.println("Has introducido datos no validos al crear la edicion");
        }
    }

    public void update() {
        try {
            Articulo articulo = readArticulo(CONN);
            updateArticulo(articulo);
            ArticuloController.update(articulo, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }

    }

    public void delete() {
        try {
            Articulo articulo = readArticulo(CONN);
            ArticuloController.deleteByIsbn(articulo.getIsbn(), CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }
    }

    public void vender() {
        try {
            Articulo articulo = readNoVendido(CONN);
            Persona cliente = PersonasMenu.readCliente(CONN);

            articulo.setDniCliente(cliente.getDni());
            ArticuloController.update(articulo, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe");
        }
    }

    public void devolver() {
        try {
            Articulo articulo = readVendido(CONN);
            articulo.setDniCliente(null);
            ArticuloController.update(articulo, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe");
        }
    }

    public static Articulo create() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        System.out.print("Introduce el dni del autor: ");
        String dniAutor = reader.nextLine();

        System.out.print("Introduce el titulo: ");
        String titulo = reader.nextLine();

        System.out.print("Introduce el dni del cliente: ");
        String dniCliente = reader.nextLine();
        dniCliente = (dniCliente.equals("")) ? null : dniCliente;

        return new Articulo(
                isbn,
                dniAutor,
                titulo,
                dniCliente);

    }

    public static void updateArticulo(Articulo articulo) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Introduce el nuevo dni del autor: ");
        articulo.setDniAutor(reader.nextLine());

        System.out.print("Introduce el nuevo titulo: ");
        articulo.setTitulo(reader.nextLine());

        System.out.print("Introduce el nuevo dni del cliente: ");
        articulo.setDniCliente(reader.nextLine());
    }

    public static Articulo readArticulo(Connection conn) throws SQLException {
        ArrayList<Articulo> articulos = ArticuloController.getAll(conn);
        articulos.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return articulos
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Articulo readNoVendido(Connection conn) throws SQLException {
        ArrayList<Articulo> noVendidos = ArticuloController.getNoVendidos(conn);
        noVendidos.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return noVendidos
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Articulo readVendido(Connection conn) throws SQLException {
        ArrayList<Articulo> vendidos = ArticuloController.getVendidos(conn);
        vendidos.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return vendidos
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }
}
