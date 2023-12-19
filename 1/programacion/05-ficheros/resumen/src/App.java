import java.io.*;

public class App {
    public static final String SAVES_PATH = "saves/";
    public static final String NOTAS_ALUMNOS = "alumnos_notas.txt";
    public static final String NOMBRES = "usa_nombres.txt";
    public static final String APELLIDOS = "usa_apellidos.txt";
    public static final String FRASES = "frases.txt";
    public static final String NUMEROS = "numeros.txt";

    public static void fileReader() {
        /*
         * El metodo read de FileReader devuelve -1 cuando llega al final del archivo
         */
        File f = new File(SAVES_PATH + FRASES);

        try (FileReader reader = new FileReader(f)) {
            int curr = reader.read();
            while (curr != -1) {
                System.out.print((char) curr);
                curr = reader.read();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferedReader() {
        /*
         * Con BufferedReader tenemos tres formas de leer archivos
         * 
         * 1. read() que funciona igual que FileReader
         * 2. readline() que devuelve la linea entera
         * 3. lines() este metodo devuelve un Stream con todas las lineas del archivo
         */
        File f = new File(SAVES_PATH + FRASES);

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            // Forma 1
            int curr = reader.read();
            while (curr != -1) {
                System.out.print((char) curr);
                curr = reader.read();
            }
            System.out.println();

            // Forma 2
            String currLine = reader.readLine();
            while (currLine != null) {
                System.out.println(currLine);
                currLine = reader.readLine();
            }
            System.out.println();

            // Forma 3
            reader.lines().forEach(System.out::println);

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferedWriter() {
        File f = new File("auto");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, false))) {
            writer.write("hola");

        } catch (IOException e) {

        }
    }

    public static void dataOutputStream() {
        File f = new File("utf.bin");

        try {
            if (!f.createNewFile())
                System.out.println("El archivo ya existe. Sobreescribiendo");
        } catch (IOException e) {
            System.err.println("No se ha podido creado el archivo");
        }

        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(f, false))) {
            writer.writeUTF("juanda");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dataInputStream() {
        File f = new File("utf.bin");

        try (DataInputStream reader = new DataInputStream(new FileInputStream(f))) {
            String s = reader.readUTF();
            System.out.println(s);

            // Si ejecutaramos lo siguiente nos saltaria una excepcion EOFException
            // s = dis.readUTF();

            // System.out.printf("int %d boolean %s String %s", n, Boolean.toString(b), s);

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        dataOutputStream();
        dataInputStream();

        File f = new File("objetos");

        // ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
        // Persona p1 = new Persona("juanda", 23);
        // Persona p2 = new Persona("jorge", 20);

        // writer.writeObject(p1);
        // writer.writeObject(p2);

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f))) {
            // Podria ser un bucle para leer TODAS las personas guardadas y hacer algo
            while (true) {
                Persona p = (Persona) reader.readObject();
                System.out.println("Nombre: " + p.nombre + ", edad: " + p.edad);
            }

        } catch (IOException e) {
            System.out.println("Ocurrio un error de entrada/salida");

        } catch (ClassNotFoundException e) {
            System.out.println("La clase a la que se recupera no se encuentra");
        }
    }
}

class Persona implements Serializable {
    public String nombre;
    public int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}

class RuedasInvalidasException extends Exception {
    public RuedasInvalidasException(String msg) {
        super(msg);
    }
}
