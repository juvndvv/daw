
import java.util.Scanner;

public class TablaMult {
    public static void main(String[] args) {
        System.out.print("Introduce un numero entero: ");
        int numero = leeEnteroValido();
        muestraTablaMultiplicar(numero);
    }

    public static int leeEnteroValido() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();


        if (!entrada.matches("[\\d]*|-\\d*")) {
            System.out.println("Por favor, introduce un numero entero");
            return leeEnteroValido();
        }

        return Integer.parseInt(entrada);
    }

    public static void muestraTablaMultiplicar(int n) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d x %d = %d%n", n, i, n * i);
        }
    }
}
