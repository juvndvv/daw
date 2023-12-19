import java.util.Scanner;

public class Ej07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numeroProducto = sc.nextLine();
        do {
            if (validaCodigoProducto(numeroProducto))
                System.out.println("Codigo valido!");
            else
                System.out.println("Codigo invalido");

            numeroProducto = sc.nextLine();
        } while (!numeroProducto.equals("0"));
        
        
        System.out.println("Fin del programa");
    }

    public static boolean validaCodigoProducto(String codigoProducto) {
        return obtieneDigitoVerificacion(codigoProducto.substring(0, codigoProducto.length()-1)) == codigoProducto.charAt(codigoProducto.length()-1);
    }

    public static char obtieneDigitoVerificacion (String numero) {
        int sumatorio = 0;

        for (int i = 1; i < numero.length(); i+=2) {
            sumatorio += Character.getNumericValue(numero.charAt(i)) * 2;
        }

        for (int i = 0; i < numero.length(); i+=2) {
            sumatorio += Character.getNumericValue(numero.charAt(i));
        }

        char digitoVerificacion = '0';
        while (sumatorio % 10 != 0) {
            digitoVerificacion++;
            sumatorio++;
        }

        return digitoVerificacion;
    }
}