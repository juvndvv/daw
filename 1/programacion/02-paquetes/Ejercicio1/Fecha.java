import java.util.Scanner;
import PackFecha.DateFunctions;

public class Fecha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce una fecha: ");
        String fecha = sc.nextLine();
        

        if (DateFunctions.esLogica(fecha))
            System.out.println("La fecha es valida");
        else
            System.out.println("La fecha no es valida");
    }
}