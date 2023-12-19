import java.util.Scanner;

public class TrianguloEquilatero  {
    
    private double base;
    private double altura;

    public double getBase() {
        return this.base;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

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
        triangulo.setBase(base);

        System.out.print("Introduce la longitud de la altura: ");
        entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud de la altura: ");
            entrada = sc.nextLine();
        }

        double altura = Double.parseDouble(entrada);
        triangulo.setAltura(altura);

        System.out.printf("Perimetro: %.2f%n", triangulo.perimetro());
        System.out.printf("Area: %.2f%n", triangulo.area());
    }
}
