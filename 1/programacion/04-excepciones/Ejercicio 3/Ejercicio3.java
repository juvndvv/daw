
import java.util.Scanner;
import java.util.InputMismatchException;

public class Ejercicio3 {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int[] vector = new int[3];

        int a = (int) Math.random() * 99;

        try {
            for (int i = 0; i < 4; i++) {
                int b = sc.nextInt();
                vector[i] = a / b;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.err.println("Error: Introduce un entero de 32 bits");
        }
    }
}