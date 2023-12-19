import java.util.Scanner;

public class Ej06 {
    public static void main(String[] args) {
        int numero = 0;
        while (numero > -1) {
            System.out.print("Introduce un numero: ");
            numero = leeEnteroValido();
            
            if (esCapicua(Integer.toString(numero)))
                System.out.println("Es capicua");
            else
                System.out.println("No es capicua");
        }

        System.out.println("Fin del programa!");
    }

    public static boolean esCapicua(String numero) {
        int i = 0;
        int j = numero.length()-1;

        while (i < j) {
            if (numero.charAt(i) != numero.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    public static int leeEnteroValido() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        try {
            return Integer.parseInt(entrada);
        } catch (Exception e) {
            System.out.print("Introduce un numero entero: ");
            return leeEnteroValido();
        }
    }
}
