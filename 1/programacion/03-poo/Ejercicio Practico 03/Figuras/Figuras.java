import java.util.Scanner;

public class Figuras {
    public static void mostrarMenu() {
        System.out.println("1. Cuadrado");
        System.out.println("2. Rectangulo");
        System.out.println("3. Triangulo equilatero");
        System.out.println("4. Circulo");
        System.out.println("5. Pentagono");
        System.out.println("6. Salir");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mostrarMenu();
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Introduce la longitud del lado: ");
                double ladoCuadrado = sc.nextDouble();
                Cuadrado cuadrado = new Cuadrado(ladoCuadrado);

                System.out.printf("Perimetro: %.2f unidades%n", cuadrado.perimetro());
                System.out.printf("Area: %.2f%n", cuadrado.area());

                break;
            case 2:
                System.out.print("Introduce la longitud de la base: ");
                double baseRectangulo = sc.nextDouble();

                System.out.print("Introduce la longitud de la altura: ");
                double alturaRectangulo = sc.nextDouble();

                Rectangulo rectangulo = new Rectangulo(baseRectangulo, alturaRectangulo);

                System.out.printf("Perimetro: %.2f unidades%n", rectangulo.perimetro());
                System.out.printf("Area: %.2f%n", rectangulo.area());

                break;
            case 3:
                System.out.print("Introduce la longitud de la base: ");
                double baseTriangulo = sc.nextDouble();

                System.out.print("Introduce la longitud de la altura: ");
                double alturaTriangulo = sc.nextDouble();
                
                TrianguloEquilatero triangulo = new TrianguloEquilatero(baseTriangulo, alturaTriangulo);

                System.out.printf("Perimetro: %.2f unidades%n", triangulo.perimetro());
                System.out.printf("Area: %.2f%n", triangulo.area());

                break;
            case 4:
                System.out.print("Introduce la longitud del radio: ");
                double radio = sc.nextDouble();

                Circulo circulo = new Circulo(radio);

                System.out.printf("Perimetro: %.2f unidades%n", circulo.perimetro());
                System.out.printf("Area: %.2f%n", circulo.area());

                break;
            case 5:
                System.out.print("Introduce la longitud del lado: ");
                double ladoPentagono = sc.nextDouble();
                
                Pentagono pentagono = new Pentagono(ladoPentagono);

                System.out.printf("Perimetro: %.2f unidades%n", pentagono.perimetro());
                System.out.printf("Area: %.2f%n", pentagono.area());


            case 6:
                System.out.println("Finalizando el programa...");
                break;
            default:
        }
    }
}
