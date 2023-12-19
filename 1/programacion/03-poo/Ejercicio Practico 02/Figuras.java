import java.util.Scanner;

public class Figuras {
    public static void mostrarMenu() {
        System.out.println("1. Cuadrado");
        System.out.println("2. Rectangulo");
        System.out.println("3. Triangulo equilatero");
        System.out.println("4. Circulo");
        System.out.println("5. Pentagono");
        System.out.println("6.. Salir");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mostrarMenu();
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                Cuadrado.prueba();
                break;
            case 2:
                Rectangulo.prueba();
                break;
            case 3:
                TrianguloEquilatero.prueba();
                break;
            case 4:
                Circulo.prueba();
                break;
            case 5:
                Pentagono.prueba();
                break;
            case 6:
                System.out.println("Finalizando el programa...");
                break;
            default:
        }
    }
}
