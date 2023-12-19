package com.jotade.Views;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.jotade.Controllers.LibroController;
import com.jotade.Database.ErrorCodes;
import com.jotade.Models.Libro;
import com.jotade.Models.Persona;

@SuppressWarnings("resource")
public class LibrosMenu extends MenuImpl {

    public LibrosMenu(Connection conn) {
        super(conn);
    }

    @Override
    public void parseOption(int option) {
        switch (option) {
            case 1 -> showAll();
            case 2 -> insert();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> prestar();
            case 6 -> devolver();
            case 0 -> System.out.println("Saliendo al menu principal...");
            default -> System.out.println("Opcion invalida");
        }
    }

    @Override
    public void show() {
        System.out.println("[LIBROS]");
        System.out.println("\t1. Ver todos");
        System.out.println("\t2. Insertar");
        System.out.println("\t3. Actualizar");
        System.out.println("\t4. Borrar");
        System.out.println("\t5. Prestar");
        System.out.println("\t6. Devolucion");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void showAll() {
        try {
            ArrayList<Libro> articulos = LibroController.getAll(CONN);
            articulos.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void insert() {
        try {
            Libro e = create();
            LibroController.save(e, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IllegalArgumentException e) {
            System.out.println("Datos invalidos");
        }
    }

    public void update() {
        try {
            Libro edicion = readLibro(CONN);
            modify(edicion);
            LibroController.update(edicion, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }

    }

    public void delete() {
        try {
            Libro libro = readLibro(CONN);
            LibroController.deleteByIsbn(libro.getIsbn(), CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }
    }

    public void prestar() {
        try {
            Libro libro = readNoPrestado(CONN);
            Persona socio = PersonasMenu.readSocio(CONN);

            libro.setDniSocio(socio.getDni());
            LibroController.update(libro, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe!");
        }
    }

    public void devolver() {
        try {
            Libro libro = readPrestado(CONN);
            libro.setDniSocio(null);
            LibroController.update(libro, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No existe!");
        }
    }

    public static Libro create() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        System.out.print("Introduce el dni del autor: ");
        String dniAutor = reader.nextLine();

        System.out.print("Introduce el titulo: ");
        String titulo = reader.nextLine();

        System.out.print("Introduce el dni del socio: ");
        String entrada = reader.nextLine();
        String dniSocio = entrada.equals("") ? null : entrada;

        return new Libro(
                isbn,
                dniAutor,
                titulo,
                dniSocio);

    }

    public static void modify(Libro libro) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Introduce el nuevo dni del autor: ");
        libro.setDniAutor(reader.nextLine());

        System.out.print("Introduce el nuevo titulo: ");
        libro.setTitulo(reader.nextLine());

        System.out.print("Introduce el nuevo dni del socio: ");
        String entrada = reader.nextLine();
        libro.setDniSocio(entrada.equals("") ? null : entrada);
    }

    public static Libro readLibro(Connection conn) throws SQLException {
        System.out.println("Libros");
        ArrayList<Libro> libros = LibroController.getAll(conn);
        libros.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return libros
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Libro readNoPrestado(Connection conn) throws SQLException {
        System.out.println("Libros no prestados:");
        ArrayList<Libro> noPrestados = LibroController.getNoPrestados(conn);
        noPrestados.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return noPrestados
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Libro readPrestado(Connection conn) throws SQLException {
        System.out.println("Libros prestados:");
        ArrayList<Libro> prestados = LibroController.getPrestados(conn);
        prestados.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return prestados
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }
}
