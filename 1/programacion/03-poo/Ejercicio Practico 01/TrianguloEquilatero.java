import java.util.Scanner;

public class TrianguloEquilatero  {
    
    public double base;
    public double altura;

    public double perimetro() {
        return base * 3;
    }

    public double area() {
        return (base * altura) / 2;
    }

    public static void prueba() {
        Scanner sc = new Scanner(System.in);

        TrianguloEquilatero triangulo = new TrianguloEquilatero();

        System.out.print("Introduce la longitud de la base: ");
        String entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud de la base: ");
            entrada = sc.nextLine();
        }

        double base = Double.parseDouble(entrada);
        triangulo.base = base;

        System.out.print("Introduce la longitud de la altura: ");
        entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud de la base: ");
            entrada = sc.nextLine();
        }

        double altura = Double.parseDouble(entrada);
        triangulo.altura = altura; 


        System.out.printf("Perimetro: %.2f%n", triangulo.perimetro());
        System.out.printf("Area: %.2f%n", triangulo.area());
    }
}
