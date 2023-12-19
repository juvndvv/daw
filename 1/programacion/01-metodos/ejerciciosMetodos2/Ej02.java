import java.util.Scanner;

public class Ej02 {
    public static void main(String[] args) {
        boolean siguiente = true;
        while (siguiente) {
            System.out.println(obtieneCalificacionAlfabetica(leeNotaValida()));
            siguiente = leeSiguiente();
        }
    }

    public static boolean leeSiguiente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Otro alumno?");
        String entrada = sc.nextLine();
        

        if (entrada.equalsIgnoreCase("si"))
            return true;
        else if (entrada.equalsIgnoreCase("no"))
            return false;
        
        System.out.println("Introduce si o no");
        return leeSiguiente();
    }

    public static String obtieneCalificacionAlfabetica(float nota) {
        String calificacionAlfabetica;
        if (nota < 3)
            calificacionAlfabetica = "Muy deficiente";
        else if (nota < 5)
            calificacionAlfabetica = "Insuficiente";
        else if (nota < 6)
            calificacionAlfabetica = "Suficiente";
        else if (nota < 9)
            calificacionAlfabetica = "Notable";
        else
            calificacionAlfabetica = "Sobresaliente";
        
        return calificacionAlfabetica;
    }
    
    public static float leeNotaValida() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        float nota;

        try {
            nota = Float.parseFloat(entrada);
            if (nota < 0 || nota > 10) {
                System.out.print("Introduce un numero real entre 0 y 10: ");
                return leeNotaValida();
            }
            return nota;

        } catch (Exception e) {
            System.out.print("La nota no es un numero real: ");
            return leeNotaValida();
        }
    }
}
