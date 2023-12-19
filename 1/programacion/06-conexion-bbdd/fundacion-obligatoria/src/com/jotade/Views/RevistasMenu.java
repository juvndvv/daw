package com.jotade.Views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.jotade.Controllers.RevistaController;
import com.jotade.Controllers.VentaRevistaController;
import com.jotade.Database.ErrorCodes;
import com.jotade.Models.Persona;
import com.jotade.Models.Revista;
import com.jotade.Models.VentaRevista;

@SuppressWarnings("resource")
public class RevistasMenu extends MenuImpl {

    public RevistasMenu(Connection conn) {
        super(conn);
    }

    @Override
    public void parseOption(int option) {
        switch (option) {
            case 1 -> showAll();
            case 2 -> insert();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> sellRevista();
            case 6 -> showAllVentasRevistas();
            case 0 -> System.out.println("Saliendo al menu principal...");
            default -> System.out.println("Opcion invalida");
        }
    }

    @Override
    public void show() {
        System.out.println("[REVISTAS]");
        System.out.println("\t1. Ver todas");
        System.out.println("\t2. Insertar");
        System.out.println("\t3. Actualizar");
        System.out.println("\t4. Borrar");
        System.out.println("\t5. Vender");
        System.out.println("\t6. Ver ventas");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void showAll() {
        try {
            ArrayList<Revista> revistas = RevistaController.getAll(CONN);
            revistas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void showAllVentasRevistas() {
        try {
            ArrayList<VentaRevista> ventaRevistas = VentaRevistaController.getAll(CONN);
            ventaRevistas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void insert() {
        try {
            Revista r = create();
            RevistaController.save(r, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IllegalArgumentException e) {
            System.out.println("Datos invalidos");
        }
    }

    public void update() {
        try {
            Revista revista = readRevista(CONN);
            modify(revista);
            RevistaController.update(revista, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }

    }

    public void delete() {
        try {
            Revista revista = readRevista(CONN);
            RevistaController.deleteByIsbn(revista.getIsbn(), CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("ISBN no encontrado");
        }
    }

    public void sellRevista() {
        try {
            Revista revista = RevistasMenu.readRevista(CONN);
            Persona cliente = PersonasMenu.readCliente(CONN);
            int ejemplares = readEjemplares();

            VentaRevista ventaRevista = revista.sell(cliente, ejemplares);
            VentaRevistaController.save(ventaRevista, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (InputMismatchException e) {
            System.out.println("Datos invalidos!");
        }
    }

    private static int readEjemplares() {
        System.out.print("Introduce el numero de ejemplares: ");
        Scanner reader = new Scanner(System.in);
        return reader.nextInt();
    }

    public static Revista create() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        System.out.print("Introduce el nombre: ");
        String nombre = reader.nextLine();

        return new Revista(
                isbn,
                nombre);
    }

    public static void modify(Revista revista) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Introduce la fecha de publicacion (yyyy-[m]m-[d]d): ");
        revista.setFechaPublicacion(Date.valueOf(reader.nextLine()));

        System.out.print("Introduce el nombre: ");
        revista.setNombre(reader.nextLine());
    }

    public static Revista readRevista(Connection conn) throws SQLException {
        // Mostramos todas las revistas
        System.out.println("Revistas:");
        ArrayList<Revista> revistas = RevistaController.getAll(conn);
        revistas.forEach(System.out::println);

        // Obtenemos la deseada
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el isbn: ");
        String isbn = reader.nextLine();

        return revistas
                .stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .collect(Collectors.toList())
                .get(0);
    }
}
