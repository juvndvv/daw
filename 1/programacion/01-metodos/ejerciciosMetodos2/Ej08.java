import java.util.Scanner;

public class Ej08 {
    public static void main(String[] args) {
        int numero = leeEnteroPositivoValido();
        System.out.printf("La suma de los digitos de %d: %d%n", numero, sumaDigitos(numero));
    }

    public static int sumaDigitos(int numero) {
        int sumatorio = 0;

        int longitudNumero = obtieneLongitud(numero);
        for (int i = 0; i < longitudNumero; i++) {
            sumatorio += obtieneDigito(i, numero);
        }

        return sumatorio;
    }   

    public static int obtieneLongitud(int numero){
        return (int) Math.floor(Math.log10(Math.abs(numero)) + 1);
    }

    public static int obtieneDigito(int posicion, int numero) {
        return numero / (int) Math.pow(10, posicion) % 10;
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
}
