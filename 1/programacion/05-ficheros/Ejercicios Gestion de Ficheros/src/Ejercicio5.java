import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Ejercicio5 {
    public static int[] extremos(File f) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        try (Scanner reader = new Scanner(f)) {
            while (reader.hasNextInt()) {
                int current = reader.nextInt();
                min = Math.min(min, current);
                max = Math.max(max, current);
            }

        } catch (FileNotFoundException fnf) {
            System.err.println("No existe el fichero");
        }

        return new int[] {min, max};
    }

    public static void main(String[] args) {
        String filename = Archivos.getNumeros();

        File f = new File(filename);
        int[] extremos = extremos(f);

        System.out.printf("Min: %d%nMax: %d%n", extremos[0], extremos[1]);
    }
}
