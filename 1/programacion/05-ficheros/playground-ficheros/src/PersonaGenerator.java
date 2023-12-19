import java.io.*;

public class PersonaGenerator {
    public static void main(String[] args) {
        read();
    }

    // Metodo que lee un archivo de objetos de tipo Persona
    public static void read() {
        File f = new File("personas.dat");

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                Persona p = (Persona) reader.readObject();
                System.out.println(p);
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public static void write() {
        File f = new File("personas.dat");

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f, true))) {
            for (int i = 0; i < 1000; i++) {
                Persona p = generate();
                writer.writeObject(p);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    public static Persona generate() {
        String nombre = generateName();
        int edad = generateAge();
        String dni = generateDni();
        String direccion = generateAddress();
        String telefono = generatePhone();
        String email = generateEmail(nombre);

        return new Persona(nombre, edad, dni, direccion, telefono, email);
    }

    private static String generateName() {
        String[] nombres = {"Juan", "Pedro", "Maria", "Jose", "Luis", "Ana", "Carlos", "Sofia", "Jorge", "Marta",
                "Alberto", "Laura", "Antonio", "Carmen", "David", "Rosa", "Daniel", "Elena", "Miguel", "Lucia",
                "Rafael", "Julia", "Francisco", "Alba", "Alejandro", "Isabel", "Manuel", "Paula", "Fernando", "Sara",
                "Pablo", "Eva", "Diego", "Clara", "Javier", "Raquel", "Ruben", "Nuria", "Sergio", "Patricia", "Victor",
                "Cristina", "Adrian", "Silvia", "Alvaro", "Eva", "Joaquin", "Beatriz", "Raul", "Elena", "Jordi", "Ines",
                "Ivan", "Alicia", "Oscar", "Celia", "Marcos", "Cristina", "Jesus", "Natalia", "Mario", "Teresa",
                "Samuel", "Sonia", "Hugo", "Marina", "Iker", "Angela", "Santiago", "Ainhoa", "Saul", "Aitana", "Bruno",
                "Aroa", "Alex", "Emma", "Oliver", "Valeria", "Martin", "Vega", "Leo", "Lola", "Marco", "Lara", "Mateo",
                "Mia", "Lucas", "Chloe", "Nicolas", "Alma", "Alejandro", "Olivia", "Daniel", "Leire", "Hector", "Irene",
                "Adrian", "Ariadna", "David", "Carla", "Aaron", "Candela", "Eric", "Daniela", "Izan", "Valentina", "Jan",
                "Vera", "Pau", "Alba", "Joel", "Lia", "Pol", "Aya", "Nil", "Ainara", "Biel", "Aina", "Oriol", "Ayla",
                "Enzo", "Amaia" };

        return nombres[(int) (Math.random() * nombres.length)];
    }

    private static int generateAge() {
        return (int) (Math.random() * 100);
    }

    private static String generateDni() {
        String dni = "";
        for (int i = 0; i < 8; i++) {
            dni += (int) (Math.random() * 10);
        }
        dni += (char) ((int) (Math.random() * 26) + 65);

        return dni;
    }

    private static String generateAddress() {
        String[] calles = {"Calle de la Piruleta", "Calle de la Gominola", "Calle de la Chuchería",
                "Calle de la Galleta", "Calle de la Magdalena", "Calle de la Napolitana", "Calle de la Tarta",
                "Calle de la Gofre", "Calle de la Rosquilla", "Calle de la Donut", "Calle de la Croqueta",
                "Calle de la Tortilla", "Calle de la Patata", "Calle de la Pizza", "Calle de la Hamburguesa",
                "Calle de la Salchicha", "Calle de la Chuleta", "Calle de la Chorizo", "Calle de la Panceta",
                "Calle de la Morcilla", "Calle de la Lenteja", "Calle de la Garbanzo", "Calle de la Judía",
                "Calle de la Alubia", "Calle de la Soja", "Calle de la Guisante", "Calle de la Zanahoria",
                "Calle de la Patata", "Calle de la Cebolla", "Calle de la Lechuga", "Calle de la Coliflor",
                "Calle de la Brócoli", "Calle de la Col", "Calle de la Espinaca", "Calle de la Berenjena",
                "Calle de la Calabaza", "Calle de la Calabacín", "Calle de la Pepino", "Calle de la Tomate",
                "Calle de la Pimiento", "Calle de la Cereza", "Calle de la Fresa", "Calle de la Frambuesa",
                "Calle de la Manzana", "Calle de la Pera", "Calle de la Naranja", "Calle de la Plátano",
                "Calle de la Uva", "Calle de la Melocotón", "Calle de la Melón", "Calle de la Sandía",
                "Calle de la Piña", "Calle de la Limón", "Calle de la Mandarina" };

        return calles[(int) (Math.random() * calles.length)] + ", " + ((int) (Math.random() * 100) + 1);
    }

    private static String generatePhone() {
        String telefono = "6";
        for (int i = 0; i < 8; i++) {
            telefono += (int) (Math.random() * 10);
        }

        return telefono;
    }

    private static String generateEmail(String nombre) {
        String[] dominios = {"gmail.com", "hotmail.com", "outlook.com", "yahoo.com", "protonmail.com", "tutanota.com",
                "zoho.com", "aol.com", "gmx.com", "icloud.com", "mail.com", "yandex.com", "gmx.us", "gmx.de",
                "gmx.co.uk", "gmx.fr", "gmx.at", "gmx.ch", "gmx.net", "gmx.es", "gmx.eu", "gmx.com.mx", "gmx.com.ar",
                "gmx.com.co", "gmx.com.ve", "gmx.com.br", "gmx.com.pe", "gmx.com.uy", "gmx.com.ec", "gmx.com.pa",
                "gmx.com.py", "gmx.com.do", "gmx.com.gt", "gmx.com.hn", "gmx.com.ni", "gmx.com.sv", "gmx.com.pr",
                "gmx.com.cu", "gmx.com.jm", "gmx.com.bo", "gmx.com.sr", "gmx.com.uy", "gmx.com.py", "gmx.com.do",
                "gmx.com.gt", "gmx.com.hn", "gmx.com.ni", "gmx.com.sv", "gmx.com.pr", "gmx.com.cu", "gmx.com.jm",
                "gmx.com.bo", "gmx.com.sr", "gmx.com.uy", "gmx.com.py", "gmx.com.do", "gmx.com.gt", "gmx.com.hn",
                "gmx.com.ni", "gmx.com.sv", "gmx.com.pr", "gmx.com.cu", "gmx.com.jm", "gmx.com.bo", "gmx.com.sr",
                "gmx.com.uy", "gmx.com.py", "gmx.com.do", "gmx.com.gt", "gmx.com.hn", "gmx.com.ni", "gmx.com.sv",
                "gmx.com.pr", "gmx.com.cu", "gmx.com.jm", "gmx.com.bo", "gmx.com.sr", "gmx.com" };

        return nombre.toLowerCase() + "@" + dominios[(int) (Math.random() * dominios.length)];
    }
}
