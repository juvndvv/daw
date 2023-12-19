import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {
    public static void muestraInfoRuta(File ruta) throws FileNotFoundException {
        if (!ruta.exists()) {
            throw new FileNotFoundException();

        } else if (ruta.isFile()) {
            System.out.printf("Nombre del archivo: %s%n", ruta.getName());

        } else if (ruta.isDirectory()) {
            List<String> content = Arrays.asList(ruta.list());

            // Mostramos los directorios ordenados alfabeticamente
            content.stream()
                    .filter(file -> new File(ruta.getAbsolutePath() + "\\" + file).isDirectory())
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .forEach(file -> System.out.printf("[*] %s%n", file));

            // Mostramos los archivos ordenados alfabeticamente
            content.stream()
                    .filter(file -> new File(ruta.getAbsolutePath() + "\\" + file).isFile())
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .forEach(directory -> System.out.printf("[A] %s%n", directory));
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        String ruta = reader.nextLine();
        while (!ruta.equals("")) {
            try {
                muestraInfoRuta(new File(ruta));
            } catch (FileNotFoundException e) {
                System.out.println("El archivo no existe! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }

            ruta = reader.nextLine();
        }
        reader.close();
        System.out.println("FIN DEL PROGRAMA");
    }
}