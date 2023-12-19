
import java.util.Scanner;

public class Primos {

    public static void main(String[] args) {
        System.out.print("Introduce un numero: ");
        int numero = leeEnteroPositivoValido();
        
        while (numero != 0){
            if (esPrimo(numero))
                System.out.println("El numero es primo");
            else
                System.out.println("El numero no es primo");
            
            System.out.print("Introduce un numero: ");
            numero = leeEnteroPositivoValido();
        }

        System.out.println("Fin del programa");
    }

    public static int leeEnteroPositivoValido() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (!entrada.matches("\\d*")){
            System.out.println("[error] Introduce un entero positivo valido");
            return leeEnteroPositivoValido();
        }

        return Integer.parseInt(entrada);
    }

    public static boolean esPrimo(int numero) {
        if (numero == 2)
            return true;

        if (numero % 2 == 0 || numero == 1)
            return false;

        for (int i = 3; i * i <= numero; i += 2) {
            if (numero % i == 0)
                return false;
        }

        return true;
    }
}