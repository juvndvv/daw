package com.jotade.Views;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.jotade.Controllers.PersonaController;
import com.jotade.Database.ErrorCodes;
import com.jotade.Models.Persona;

@SuppressWarnings("resource")
public class PersonasMenu extends MenuImpl {

    public PersonasMenu(Connection conn) {
        super(conn);
    }

    @Override
    public void parseOption(int option) {
        switch (option) {
            case 1 -> showAll();
            case 2 -> showClientes();
            case 3 -> showSocios();
            case 4 -> showAutores();
            case 5 -> insert();
            case 6 -> addTipo();
            case 7 -> deleteTipo();
            case 8 -> update();
            case 9 -> delete();
            case 0 -> System.out.println("Saliendo al menu principal...");
            default -> System.out.println("Opcion invalida");
        }
    }

    @Override
    public void show() {
        System.out.println("[PERSONAS]");
        System.out.println("\t1. Ver todas");
        System.out.println("\t2. Ver clientes");
        System.out.println("\t3. Ver socios");
        System.out.println("\t4. Ver autores");
        System.out.println("\t5. Insertar");
        System.out.println("\t6. Agregar tipo");
        System.out.println("\t7. Eliminar tipo");
        System.out.println("\t8. Actualizar informacion");
        System.out.println("\t9. Eliminar");
        System.out.println("\t0. Salir");
        System.out.print("-> ");
    }

    public void showAll() {
        try {
            ArrayList<Persona> personas = PersonaController.getAll(CONN);
            personas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void showClientes() {
        try {
            ArrayList<Persona> personas = PersonaController.getClientes(CONN);
            personas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void showSocios() {
        try {
            ArrayList<Persona> personas = PersonaController.getSocios(CONN);
            personas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void showAutores() {
        try {
            ArrayList<Persona> personas = PersonaController.getAutores(CONN);
            personas.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));
        }
    }

    public void insert() {
        try {
            Persona e = create();
            PersonaController.save(e, CONN);

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Datos invalidos");
        }
    }

    public void addTipo() {
        try {
            Persona currPersona = readPersona(CONN);

            // Obtenemos los tipos de la persona y los mostramos
            ArrayList<String> tipos = PersonaController.getTiposByDni(currPersona.getDni(), CONN);
            System.out.printf("La persona con dni %s tiene los siguientes tipos: %n", currPersona.getDni());
            tipos.forEach(tipo -> System.out.printf("- %s%n", tipo));

            // Valoramos los que tiene y si se pueden añadir
            ArrayList<String> tiposDisponibles = Persona.TIPOS
                    .stream()
                    .filter(f -> !tipos.contains(f))
                    .collect(Collectors.toCollection(ArrayList::new));

            if (tiposDisponibles.size() != 0) {
                System.out.println("Introduce el tipo que desees añadir: ");

                for (int i = 0; i < tiposDisponibles.size(); i++)
                    System.out.printf("%d. %s%n", i + 1, tiposDisponibles.get(i));

                Scanner reader = new Scanner(System.in);
                int tipoPos = reader.nextInt();

                currPersona.setTipo(tiposDisponibles.get(tipoPos - 1));
                PersonaController.save(currPersona, CONN);
                System.out.println("Tipo añadido a la persona");

            } else {
                System.out.println("Tiene todos los tipos");
            }

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Introduce un numero");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] No existe");
        }
    }

    public void deleteTipo() {
        try {
            Persona currPersona = readPersona(CONN);

            // Obtenemos los tipos de la persona y los mostramos
            ArrayList<String> tipos = PersonaController.getTiposByDni(currPersona.getDni(), CONN);
            System.out.printf("La persona con dni %s tiene los siguientes tipos: %n", currPersona.getDni());

            for (int i = 0; i < tipos.size(); i++)
                System.out.printf("%d. %s%n", i + 1, tipos.get(i));

            // Se obtiene el indice del tipo a eliminar
            System.out.println("Introduce el tipo que desees eliminar: ");
            Scanner reader = new Scanner(System.in);
            int tipoPos = reader.nextInt();

            // Se elimina
            PersonaController.deleteByDniAndTipo(currPersona.getDni(), tipos.get(tipoPos - 1), CONN);
            System.out.println("Tipo eliminado");

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Introduce un numero");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] No existe");
        }
    }

    public void update() {
        try {
            Persona currPersona = readPersona(CONN);
            modify(currPersona);
            PersonaController.update(currPersona, CONN);
            System.out.println("Persona actualizada");

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] No existe");
        }
    }

    public void delete() {
        try {
            Persona persona = readPersona(CONN);
            PersonaController.deleteByDni(persona.getDni(), CONN);
            System.out.println("Persona borrada");

        } catch (SQLException e) {
            System.out.println(ErrorCodes.getMessage(e.getErrorCode()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Dni no encontrado");
        }
    }

    public static Persona create() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el dni: ");
        String dni = reader.nextLine();

        System.out.print("Introduce el nombre: ");
        String nombre = reader.nextLine();

        System.out.println("Introduce el telefono: ");
        String telefono = reader.nextLine();

        Map<Integer, String> tipoMap = new HashMap<>() {
            {
                put(1, Persona.SOCIO);
                put(2, Persona.CLIENTE);
                put(3, Persona.AUTOR);
            }
        };

        System.out.print("Selecciona el tipo [1=SOCIO, 2=CLIENTE, 3=AUTOR]: ");
        String tipo = tipoMap.getOrDefault(reader.nextInt(), "");

        if (tipo.equals(""))
            throw new IllegalArgumentException();

        return new Persona(
                dni,
                nombre,
                telefono,
                tipo);
    }

    public static void modify(Persona persona) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre: ");
        persona.setNombre(sc.nextLine());

        System.out.print("Introduce el telefono: ");
        persona.setTelefono(sc.nextLine());
    }

    public static Persona readCliente(Connection conn) throws SQLException {
        System.out.println("Clientes:");
        ArrayList<Persona> clientes = PersonaController.getClientes(conn);
        clientes.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el dni: ");
        String dni = reader.nextLine();

        return clientes
                .stream()
                .filter(c -> c.getDni().equals(dni))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Persona readSocio(Connection conn) throws SQLException {
        System.out.println("Socios:");
        ArrayList<Persona> socios = PersonaController.getSocios(conn);
        socios.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el dni: ");
        String dni = reader.nextLine();

        return socios
                .stream()
                .filter(e -> e.getDni().equals(dni))
                .collect(Collectors.toList())
                .get(0);
    }

    public static Persona readPersona(Connection conn) throws SQLException {
        System.out.println("Personas:");
        ArrayList<Persona> personas = PersonaController.getDistinct(conn);
        personas.forEach(System.out::println);

        Scanner reader = new Scanner(System.in);
        System.out.print("Introduce el dni: ");
        String dni = reader.nextLine();

        return personas
                .stream()
                .filter(e -> e.getDni().equals(dni))
                .collect(Collectors.toList())
                .get(0);
    }
}
