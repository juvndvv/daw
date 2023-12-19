import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        int[][] notas = new int[4][3];
        leeNotas(notas);
        mostrarMedias(notas);
    }

    public static void mostrarMedias(int[][] notas) {
        int i = 1;
        for (int[] notasAlumno: notas) {
            float media = calculaMedia(notasAlumno);
            System.out.printf("La nota media del alumno %d: %.2f%n", i++, media);
        }
    }

    public static float calculaMedia(int[] notas) {
        int sumatorio = 0;

        for (int nota: notas) {
            sumatorio += nota;
        }

        return sumatorio / notas.length;
    }

    public static void leeNotas(int[][] notas) {
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                int currNota = leeNota(i+1, j+1);

                while (currNota == -1) {
                    System.out.print("ERROR: La nota introducida no es valida. ");
                    currNota = leeNota(i+1, j+1);
                }

                notas[i][j] = currNota;
            }
        }
    }

    public static int leeNota(int alumno, int evaluacion) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Introduce la nota del alumno %d de la %dº evaluacion: ", alumno, evaluacion);
        String entrada = sc.nextLine();

        if (!esNumerico(entrada))
            return -1;
        
        if (!esNotaValida(entrada))
            return -1;

        return Integer.valueOf(entrada);
    }

    public static boolean esNumerico(String str) {
        return str.matches("\\d+");
    }

    public static boolean esNotaValida(String str) {
        int nota = Integer.parseInt(str);
        return nota >= 0 && nota <= 10;
    }

}