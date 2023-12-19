
import java.util.Scanner;

public class Millas {
    public static void main(String[] args) {
        System.out.print("Introduce una distancia en millas: ");
        float distanciaEnMillas = leeMillasValidas();
        
        float distanciaEnMetros = convierteMillasAMetros(distanciaEnMillas);
        System.out.printf("La distancia en metros: %.2f", distanciaEnMetros);
    }

    public static float convierteMillasAMetros(float millas) {
        return millas * 1609.34f;
    }

    public static float leeMillasValidas() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        

        if (!entrada.matches("\\d*[[.][\\d*]+]+")) {
            System.out.println("Formato: [\\d*[.]\\d*]");
            return leeMillasValidas();
        }
        
        return Float.parseFloat(entrada);
    }
}
