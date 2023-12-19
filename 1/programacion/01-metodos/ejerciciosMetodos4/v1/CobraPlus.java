package practicas.ejerciciosMetodos4;

import java.util.Scanner;

public class CobraPlus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continuar = "";
        while (!continuar.equalsIgnoreCase("no")) {
            procesaCliente();
            System.out.println();
            System.out.print("Continuar? (Escribe \"no\" para salir): ");
            continuar = sc.nextLine();
        }
        System.out.println("FIN DEL PROGRAMA");
    }

    // Pide los datos del cliente, realiza los calculos necesarios y muestra el informe
    public static void procesaCliente(){
        // Pedimos los datos del cliente
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre: ");
        String nombre = sc.nextLine();

        String numeroContrato = "";
        float potenciaContratada = -1f;
        int lecturaAnterior = -1;
        int lecturaActual = -1;

        while (numeroContrato == "") {
            System.out.print("Introduce el numero del contrato: ");
            numeroContrato = numContrato();
        }
        
        while (potenciaContratada == -1f) {
            System.out.print("Introduce la potencia contratada: ");
            potenciaContratada = potContratada();
        }

        while (lecturaAnterior == -1) {
            System.out.print("Introduce la lectura anterior del contador: ");
            lecturaAnterior = kWConsumidos();
        }

        while (lecturaActual == -1){
            System.out.print("Introduce la lectura actual del contador: ");
            lecturaActual = kWConsumidos();
        }

        // Realizamos los calculos necesarios
        int kWConsumidos = lecturaActual - lecturaAnterior;
        float importe = calculaImporte(kWConsumidos, potenciaContratada);
        float incremento = calculaIncremento(kWConsumidos, importe);

        // Mostramos el informe del cliente
        imprimeInforme(numeroContrato, nombre, kWConsumidos, potenciaContratada, importe, incremento);

    }

    // Imprime el informe por la pantalla
    public static void imprimeInforme(String numeroContrato, String nombre, int kWConsumidos, float potenciaContratada, float importe, float incremento) {
        System.out.println("-------------------------------------------");
        System.out.printf("%s Nº%s%n", nombre, numeroContrato);
        System.out.println("-------------------------------------------");
        System.out.printf("Total consumo: %d kWh%n", kWConsumidos);
        System.out.printf("Potencia contratada: %.2f kW%n", potenciaContratada);
        System.out.printf("Incremento: %.2f$%n", incremento);
        System.out.printf("Total importe facturado: %.2f$%n", importe + incremento);
    }

    // Pedir el numero de contrato validando errores
    public static String numContrato() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        // Si la entrada no coincide con el formato ddd-dddd
        //  devolvemos una cadena vacia
        if (entrada.matches("\\d{3}-\\d{4}"))
            return entrada;
        return "";
    }

    // Lee la potencia contratada validando errores
    public static float potContratada() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        // Intentamos devolver la entrada convertida a float,
        //  y comprobamos que sea mayor que 0,
        //  si algo falla devolvemos -1
        float potenciaContratada;
        try {
            potenciaContratada = Float.parseFloat(entrada);

            if (potenciaContratada > 0)
                return potenciaContratada;
            else
                System.out.println("NUMERO NO VALIDO!!!");

        } catch (Exception e) {
            System.out.println("NO ES UN NUMERO!!!");
        }

        return -1f;
    }

    // Lee lectura de kW consumidos validando errores
    public static int kWConsumidos() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        // Intentamos devolver la entrada convertida a entero,
        //  y comprobamos que sea mayor que 0,
        //  si algo falla devolvemos -1
        int kWConsumidos;
        try {
            kWConsumidos = Integer.parseInt(entrada);

            if (kWConsumidos > 0)
                return kWConsumidos;
            else
                System.out.println("NUMERO NO VALIDO!!!");

        } catch (Exception e) {
            System.out.println("NO ES UN NUMERO!!!");
        }

        return -1;
    }

    // Calcula el importe
    public static float calculaImporte(int kWConsumidos, float potenciaContratada) {
        float importe = 0;

        // Tarifa fija
        if (potenciaContratada < 2.30f)
            importe += 7.29f;
        else if (potenciaContratada < 3.45f)
            importe += 10.94f;
        else if (potenciaContratada < 4.6f)
            importe += 14.58f;
        else if (potenciaContratada < 5.75f)
            importe += 18.23f;
        else if (potenciaContratada <6.9f)
            importe += 21.87f;
        else
            importe += 8.05f;

        // Tarifa variable
        importe += (float) kWConsumidos * 0.37204f;

        return importe;
    }

    // Calcula el incremento
    public static float calculaIncremento(int kWConsumidos, float importe) {
        if (kWConsumidos < 150)
            return 0;
        if (kWConsumidos < 300)
            return importe * .05f;
        if (kWConsumidos < 400)
            return importe * .08f;
        return importe * .12f;
    }
}