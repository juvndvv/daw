import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {
    public static void muestraInfoRuta(File ruta) throws FileNotFoundException {
        if (!ruta.exists()) {
            throw new FileNotFoundException();

        } else if (ruta.isFile()) {
            System.out.printf("Nombre del archivo: %s%n", ruta.getName());

        } else if (ruta.isDirectory()) {
            List<String> content = List.of(ruta.list());

            // Mostramos los directorios
            content.stream()
                    .filter(file -> new File(ruta.getAbsolutePath() + "\\" + file).isDirectory())
                    .forEach(directory -> System.out.printf("\"[*] %s%n", directory));

            // Mostramos los archivos
            content.stream()
                    .filter(file -> new File(ruta.getAbsolutePath() + "\\" + file).isFile())
                    .forEach(file -> System.out.printf("[A] %s%n", file));
        } else {
            System.err.println("Ocurrio algo inesperado");
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        String ruta = reader.nextLine();
        while (!ruta.equals("")) {
            try {
                muestraInfoRuta(new File(ruta));
            } catch (FileNotFoundException fnf) {
                System.out.println("El archivo no existe!");
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }

            ruta = reader.nextLine();
        }

        System.out.println("FIN DEL PROGRAMA");
    }
}
