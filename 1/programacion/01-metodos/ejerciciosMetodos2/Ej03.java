
import java.util.Scanner;

public class Ej03 {
    public static void main(String[] args) {
        int[] hora = new int[3];
        hora[0] = leeHora();
        hora[1] = leeMinutos();
        hora[2] = leeSegundos();

        int segundos = 10;
        while (segundos != 0) {
            System.out.println(anadeSegundo(hora));
            segundos--;
        }
    }

    public static String anadeSegundo(int[] horaCompleta) {
        if (horaCompleta[2] != 59) {
            horaCompleta[2]++;
        } else if (horaCompleta[1] != 59) {
            horaCompleta[2] = 0;
            horaCompleta[1]++;
        } else if (horaCompleta[0] != 23) {
            horaCompleta[2] = 0;
            horaCompleta[1] = 0;
            horaCompleta[0]++;
        } else {
            horaCompleta[2] = 0;
            horaCompleta[1] = 0;
            horaCompleta[0] = 0;
        }

        return String.format("%02d:%02d:%02d",
                horaCompleta[0],
                horaCompleta[1],
                horaCompleta[2]);
    }

    public static int leeHora() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        try {
            int hora = Integer.parseInt(entrada);

            if (hora < 0 || hora > 23) {
                System.out.print("Introduce una hora valida [0-23]: ");
                return leeHora();
            }
            return hora;

        } catch (Exception e) {
            System.out.println("Introduce solo numeros: ");
            return leeHora();
        }
    }

    public static int leeMinutos() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        try {
            int minutos = Integer.parseInt(entrada);

            if (minutos < 0 || minutos > 59) {
                System.out.print("Introduce minutos validos [0-59]: ");
                return leeMinutos();
            }
            return minutos;

        } catch (Exception e) {
            System.out.println("Introduce solo numeros: ");
            return leeMinutos();
        }
    }

    public static int leeSegundos() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        try {
            int hora = Integer.parseInt(entrada);

            if (hora < 0 || hora > 59) {
                System.out.print("Introduce una segundos validos [0-59]: ");
                return leeSegundos();
            }
            return hora;

        } catch (Exception e) {
            System.out.println("Introduce solo numeros: ");
            return leeSegundos();
        }
    }
}