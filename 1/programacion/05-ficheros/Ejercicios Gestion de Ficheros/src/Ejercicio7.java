import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio7 {

    final static File NAMES_FILE = new File(Archivos.getNombres());
    final static File SURNAMES_FILE = new File(Archivos.getApellidos());

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int nNames = readPositiveInteger(reader);

        File saveFilename = null;
        if (nNames > 0) {
            try {
                saveFilename = getSaveFile(reader);

            } catch (IOException e) {
                System.err.println("Error. " + e.getMessage());
            }
        }
        reader.close();

        // TODO se esta creando un BufferedReader para cada linea que se esta añadiendo. NO OPTIMO
        if (saveFilename != null) {
            while (nNames != 0) {
                try {
                String name = generateRandomName();
                appendLine(saveFilename, name);
                } catch (IOException e) {
                    System.err.println("Error. " + e.getMessage());
                }
                nNames--;
            }
        }
    }

    public static String generateRandomName() throws IOException {
        String name = getRandomLineFromFile(NAMES_FILE);
        String surname = getRandomLineFromFile(SURNAMES_FILE);
        return name + " " + surname;
    }

    public static String getRandomLineFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String randomLine = null;
        Random rand = new Random();
        int lineNumber = 0;

        String line;
        while ((line = br.readLine()) != null) {
            if (rand.nextInt(++lineNumber) == 0) {
                randomLine = line;
            }
        }

        br.close();
        fr.close();

        return randomLine;
    }

    public static void appendLine(File file, String line) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public static File getSaveFile(Scanner reader) throws IOException {
        System.out.print("Introduce el nombre del archivo: ");
        String filename = reader.nextLine();
        File f = new File(filename);

        boolean created = f.createNewFile();

        if (created)
            System.out.println("Se ha creado el archivo");

        return f;
    }

    public static int readPositiveInteger(Scanner reader) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                System.out.print("Ingrese un numero entero: ");
                return reader.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada invalida. Intentelo de nuevo");
                reader.nextLine();
                attempts++;
            }
        }
        reader.nextLine();

        return 0;
    }
}
