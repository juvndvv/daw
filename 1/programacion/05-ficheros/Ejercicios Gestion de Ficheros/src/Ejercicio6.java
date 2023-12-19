import java.io.*;

public class Ejercicio6 {

    public static void main(String[] args) {
        File f = new File(Archivos.getNotasAlumnos());
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                System.out.printf("Media de %s: %.2f%n", obtenerNombre(linea), calcularMedia(linea));
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("No existe el archivo");
        }
    }

    public static String obtenerNombre(String linea) {
        String[] partes = linea.split(" ");
        return partes[0] + " " + partes[1];
    }

    public static double calcularMedia(String linea) {
        String[] partes = linea.split(" ");
        int total = 0;
        for (int i = 2; i < partes.length; i++) {
            total += Integer.parseInt(partes[i]);
        }
        return (double) total / (partes.length - 2);
    }
}
