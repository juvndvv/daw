import java.util.Scanner;

public class Ej01v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el angulo en grados: ");
        double anguloGrados = sc.nextDouble();
        muestraInformacionAngulo(anguloGrados);
    }

    public static void muestraInformacionAngulo(double anguloGrados) {
        double[] relacionesTrigonometricasBasicas = obtieneRelacionesTrigonometricasBasicas(anguloGrados);
        System.out.printf("Seno de %.2f: %.2f%n", anguloGrados, relacionesTrigonometricasBasicas[0]);
        System.out.printf("Coseno de %.2f: %.2f%n", anguloGrados, relacionesTrigonometricasBasicas[1]);
        System.out.printf("Tangente de %.2f: %.2f%n", anguloGrados, relacionesTrigonometricasBasicas[2]);
    }

    public static double[] obtieneRelacionesTrigonometricasBasicas(double anguloGrados) {
        double anguloRadianes = Math.toRadians(anguloGrados);
        return new double[] {
                Math.sin(anguloRadianes),
                Math.cos(anguloRadianes),
                Math.tan(anguloRadianes)
            };
    }
}