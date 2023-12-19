package practicas.ejerciciosMetodos3;
import java.util.Scanner;

public class Ej05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        int n1, n2, n3;

        do {
            opcion = leeOpcionValida();
            switch (opcion) {
                // Factorial
                case 1:
                    System.out.print("Introduce un numero: ");
                    n1 = sc.nextInt();
                    System.out.printf("El factorial de %d: %d%n", n1, factorial(n1));
                    sc.nextLine();
                    sc.nextLine();
                    break;

                // Amigos
                case 2:
                    System.out.print("Introduce un numero: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce otro numero: ");
                    n2 = sc.nextInt();

                    if (sonAmigos(n1, n2))
                        System.out.println("Los numeros son amigos");
                    else
                        System.out.println("Los numeros no son amigos");
                    sc.nextLine();
                    sc.nextLine();
                    break;

                // Ecuacion de segundo grado
                case 3:
                    System.out.print("Introduce A: ");
                    n1 = sc.nextInt();
                    System.out.print("Introduce B: ");
                    n2 = sc.nextInt();
                    System.out.print("Introduce C: ");
                    n3 = sc.nextInt();

                    double[] resultado = resuelveEcuacionSegundoGrado(n1, n2, n3);
                    int nSoluciones = 0;
                    try {
                        for (double s: resultado){
                            System.out.println(s);
                            nSoluciones++;
                        }
                    } catch (Exception e) {

                    } finally {
                        if (nSoluciones == 0)
                            System.out.println("Ninguna solucion");
                        else if (nSoluciones == 1)
                            System.out.println("Una solucion");
                        else
                            System.out.println("Dos soluciones");
                    }
                    sc.nextLine();
                    sc.nextLine();
                    break;

                // Finalizar programa
                case 4:
                    System.out.println("Fin del programa");
                    break;
                
                default:
                    System.out.println("Opcion no valida!");
            }
        } while (opcion != 4);
    }

    // Menu
    public static void muestraMenu() {
        System.out.println("1 - Calcular el factorial de un numero");
        System.out.println("2 - Hallar si dos numeros son amigos");
        System.out.println("3 - Resolver ecuacion de 2 grado");
        System.out.println("4 - Salir del programa");
    }

    public static int leeOpcionValida() {
        Scanner sc = new Scanner(System.in);
        muestraMenu();
        return sc.nextInt();
    }

    // Ecuacion de segundo grado
    public static double[] resuelveEcuacionSegundoGrado(int a, int b, int c) {
        double[] solucion;

        // Calculamos el discriminante
        double discriminante = (Math.pow(b, 2) - (4 * a * c));

        // Existe una solucion
        if (discriminante == 0) {
            solucion = new double[1];
            solucion[0] = ((-b) - (4 * a * c)) / (2 * a);

        // Existen dos soluciones
        } else if (discriminante > 0) {
            solucion = new double[2];
            solucion[0] = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
            solucion[1] = ((-b) - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        
        // No existe ninguna solucion
        } else
            solucion = null;

        return solucion;
    }

    // Factorial
    public static int factorial(int n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    // Amigos
    public static boolean sonAmigos(int a, int b) {
        return sumaDivisores(a) == b && sumaDivisores(b) == a;
    }

    public static boolean esDivisor(int divisor, int cociente) {
        return divisor % cociente == 0;
    }

    public static int sumaDivisores(int numero) {
        int suma = 0;
        for (int i = 1; i < numero; i++) {
            if (esDivisor(numero, i))
                suma += i;
        }
        return suma;
    }
}
