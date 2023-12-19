import java.util.Scanner;

public class Ej01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el angulo en grados: ");
        double anguloGrados = sc.nextDouble();
        

        muestraInformacionAngulo(anguloGrados);
    }

    public static void muestraInformacionAngulo(double anguloGrados) {
        double anguloRadianes = Math.toRadians(anguloGrados);
        System.out.printf("Seno de %.2f: %.2f%n", anguloGrados, Math.sin(anguloRadianes));
        System.out.printf("Coseno de %.2f: %.2f%n", anguloGrados, Math.cos(anguloRadianes));
        System.out.printf("Tangente de %.2f: %.2f%n", anguloGrados, Math.tan(anguloRadianes));
    }
}