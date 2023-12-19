import java.util.Scanner;

public class Cuadrado {

    private double lado;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double perimetro() {
        return lado * 4;
    }

    public double area() {
        return lado * lado;
    }

    public static void prueba() {
        Scanner sc = new Scanner(System.in);

        Cuadrado cuadrado = new Cuadrado();

        System.out.print("Introduce la longitud del lado: ");
        String entrada = sc.nextLine();

        // validar que sea un numero
        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud del lado: ");
            entrada = sc.nextLine();
        }

        double lado = Double.parseDouble(entrada);
        cuadrado.setLado(lado);

        System.out.printf("Perimetro: %.2f%n", cuadrado.perimetro());
        System.out.printf("Area: %.2f%n", cuadrado.area());
    }
}