import java.util.Scanner;

public class Ej05 {
    public static void main(String[] args) {
        int intentos = 3;
        int numeroMaximo = -1;
        while (intentos != 0 && numeroMaximo == -1) {
            System.out.print("Introduce un numero: ");
            numeroMaximo = leeEnteroPositivo();
            intentos--;
        }

        if (intentos != 0) {
            int numero = 2;
            while (numero < numeroMaximo) {
                if (esPrimo(numero))
                    System.out.println(numero);
                numero++;
            }
        }

        System.out.println("Fin del programa!");
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

    public static boolean esPrimo(int numero) {
        if (numero == 2)
            return true;

        if (numero % 2 == 0)
            return false;

        for (int i = 3; i * i <= numero; i += 2) {
            if (numero % i == 0)
                return false;
        }
        return true;
    }
}
