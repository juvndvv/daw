package CuentaCorriente;

import java.util.Scanner;
import java.util.ArrayList;

public class DawBank {

    public static void mostrarMenuCajero() {
        System.out.print("Pulsa ENTER para continuar ");
    }

    public static void mostrarMenuCliente(){
        System.out.println("1. Ingresar");
        System.out.println("2. Retirar");
        System.out.println("3. Ver movimientos");
        System.out.println("4. Salir");
    }

    public static int obtenerOpcionCliente() {
        Scanner sc = new Scanner(System.in);

        mostrarMenuCliente();

        int opcion;
        try {
        opcion = sc.nextInt();
        } catch (Exception e) {
            opcion = -1;
        }

        return opcion;
    }

    public static String obtenerIBAN() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el IBAN: ");
        return sc.nextLine();
    }

    public static String obtenerNombre() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del titular: ");
        return sc.nextLine();
    }

    public static CuentaBancaria obtenerCuenta() {
        String iban;
        String nombre;

        int intentos = 0;
        while (intentos < 3) {
            iban = obtenerIBAN();
            nombre = obtenerNombre();

            try {
                return new CuentaBancaria(iban, nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                intentos++;
            }
        }
        return null;
    }

    public static void procesarIngreso(CuentaBancaria c) {
        Scanner sc = new Scanner(System.in);

        boolean correcto = false;
        float cantidad;

        System.out.print("Introduce la cantidad: ");

        try {
            cantidad = sc.nextFloat();
            c.ingreso(cantidad);
            correcto = true;
        } catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Introduce un numero");
        }

        if (correcto)
            System.out.println("Ingreso realizado correctamente!...");
        else
            System.out.println("ERROR: No se ha podido realizar el ingreso");
    }

    public static void procesarRetirada(CuentaBancaria c) {
        Scanner sc = new Scanner(System.in);

        boolean correcto = false;
        float cantidad;

        System.out.print("Introduce la cantidad: ");

        try {
            cantidad = sc.nextFloat();
            c.retirada(cantidad);
            correcto = true;
        } catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Introduce un numero");
        }

        if (correcto)
            System.out.println("Retirada realizada correctamente!...");
        else
            System.out.println("ERROR: No se ha podido realizar la retirada");
    }

    public static void mostrarMovimientos(CuentaBancaria c) {
        ArrayList<String> movimientos = c.getMovimientos();

        for (int i = 0; i < movimientos.size(); i++) {
            System.out.println(movimientos.get(i));
        }
    }

    public static void procesarCliente() {
        CuentaBancaria cuenta = obtenerCuenta();

        if (cuenta != null) {
            int opcion = obtenerOpcionCliente();
            while (opcion != 4) {
                if (opcion == 1) {
                    procesarIngreso(cuenta);
                }
                else if (opcion == 2)
                    procesarRetirada(cuenta);
                else if (opcion == 3)
                    mostrarMovimientos(cuenta);
                else if (opcion == 4)
                    System.out.println("Finalizando la sesion...");
                else
                    System.out.println("Opcion incorrecta...");
                
                opcion = obtenerOpcionCliente();
            }
        } 
    }

    public static boolean passwdCorrecto(char[] passwd, char[] entry) {

        if (passwd.length != entry.length) 
            return false;

        for (int i = 0; i < passwd.length; i++) {
            if (passwd[i] != entry[i])
                return false; 
        }

        return true;
    }

    public static void main(String[] args) {

        // simula una contraseña de admin
        char[] passwd = {'9', '9', '5', '0'};

        mostrarMenuCajero();
        char[] opcion = System.console().readPassword();
        while (!passwdCorrecto(passwd, opcion)) {
            procesarCliente();
            mostrarMenuCajero();
            opcion = System.console().readPassword();
        }
        System.out.println("El administrador ha apagado el sistema");
    }
}
