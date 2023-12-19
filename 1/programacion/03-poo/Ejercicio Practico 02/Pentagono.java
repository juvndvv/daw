import java.util.Scanner;

public class Pentagono {

    private double lado;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double perimetro() {
        return 5 * lado;
    }

    public double area() {
        return perimetro() * apotema() / 2;
    }

    private double apotema() {
        return this.lado / (2 * Math.tan(Math.toRadians(36)));
    }

    public static void prueba() {
        Scanner sc = new Scanner(System.in);
        Pentagono pentagono = new Pentagono();

        System.out.print("Introduce la longitud del lado: ");
        String entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud del lado: ");
            entrada = sc.nextLine();
        }

        double lado = Double.parseDouble(entrada);
        pentagono.setLado(lado);

        System.out.printf("Perimetro: %.2f unidades%n", pentagono.perimetro());
        System.out.printf("Area: %.2f%n", pentagono.area());

    }
}
