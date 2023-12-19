import java.util.Scanner;

public class Ej04 {
    public static void main(String[] args) {
        // Pedimos el numero de lanzamientos con 3 intentos
        int intentos = 3;
        int numeroLanzamientos = -1;
        while (intentos != 0 && numeroLanzamientos == -1) {
            System.out.print("Introduce el numero de lanzamientos: ");
            numeroLanzamientos = leeEnteroPositivo();
            intentos--;
        }

        if (intentos != 0) {
            // Ejecucion del programa
            float[] resultados = new float[12];

            int lanzamientosRealizados = 0;
            while (lanzamientosRealizados < numeroLanzamientos) {
                String resultado = lanzaDosDados();
                resultados[Integer.parseInt(resultado)-1]++;
                lanzamientosRealizados++;
            }

            // Mostrar la frecuencia
            int i = 0;
            while (i < resultados.length) {
                System.out.printf("Frecuencia de apariciones del %d: %.2f%c%n", i+1, (resultados[i] / numeroLanzamientos) * 100, '%');
                i++;
            }
        }

        System.out.println("Fin del programa!");

    }

    public static String lanzaDosDados() {
        return Integer.toString((int) (Math.random()* 12 + 1));
    }

    public static int leeEnteroPositivo() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        try {
            int numero = Integer.parseInt(entrada);
            if (numero < 1)
                return -1;
            return numero;
        } catch (Exception e) {
            return -1;
        }
    }
}
