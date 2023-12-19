import java.util.Scanner;

public class Rectangulo {

    public double base;
    public double altura;

    public double perimetro() {
        return (base + altura) * 2;
    }

    public double area() {
        return base * altura;
    }

    public static void prueba() {
        Scanner sc = new Scanner(System.in);

        Rectangulo rectangulo = new Rectangulo();

        System.out.print("Introduce la longitud de la base: ");
        String entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud de la base: ");
            entrada = sc.nextLine();
        }

        double base = Double.parseDouble(entrada);
        rectangulo.base = base;
        
        System.out.print("Introduce la longitud de la altura: ");
        entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud de la base: ");
            entrada = sc.nextLine();
        }

        double altura = Double.parseDouble(entrada);
        rectangulo.altura = altura; 

        System.out.printf("Perimetro: %.2f%n", rectangulo.perimetro());
        System.out.printf("Area: %.2f%n", rectangulo.area());

    }
}
