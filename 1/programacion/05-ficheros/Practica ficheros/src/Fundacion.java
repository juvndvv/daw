
import java.io.*;
import edicion.*;
import java.util.ArrayList;

public class Fundacion {
    public static final File FILENAME = new File("ediciones.txt");

    public static void informe() {
        int ingresosTotales = 0;

        try {
            FileInputStream fi = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fi);

            while (fi.available() > 0) {
                Edicion e = (Edicion) ois.readObject();

                System.out.println("[INFO]");
                System.out.println(e.info() + "\n");

                try {
                    ingresosTotales += e.getIngresos();
                } catch (UnsupportedOperationException ignored) {}

            }

            System.out.println("Ingresos totales: " + ingresosTotales + " euros");

            ois.close();

        } catch (FileNotFoundException e) {
            System.err.println("No existe el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error de programa");
        }
    }

    public static void generaArchivo(){
        Revista r1 = new Revista("55656", "23-11-1999", "El Mundo");
        Libro l1 = new Libro("894536", "28-11-1947", "Juan Daniel Forner", "Mein Kampf", false);
        Articulo a1 = new Articulo("54653", "08-09-1999", "Jorge Garriga", "Como perder la cabeza");

        r1.vender();
        r1.vender();

        a1.vender();
        a1.vender();
        a1.vender();

        ArrayList<Edicion> ediciones = new ArrayList<>();

        ediciones.add(r1);
        ediciones.add(l1);
        ediciones.add(a1);

        try {
            FileOutputStream fs = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fs);

            for (Edicion edicion : ediciones) {
                oos.writeObject(edicion);
            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Genera el archivo de prueba si no existe
        if (FILENAME.exists()) {
            System.out.println("El archivo de prueba no existe!");
            System.out.println("Generando archivo de prueba...");
            generaArchivo();
        }

        informe();
    }
}
