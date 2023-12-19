import Animales.ExcepcionEdadNegativa;
import Animales.ExcepcionNombreCorto;
import Animales.Gato;

public class Main {

    public static void instanciaGato(String nombre, int edad) {
        try {
            Gato gato = new Gato(nombre, edad);
            System.out.println("Gato instanciado correctamente");

        } catch (ExcepcionNombreCorto | ExcepcionEdadNegativa e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        instanciaGato("gat", -1);
        instanciaGato("d", 12);
        instanciaGato("d", -2);
        instanciaGato("Sultan", 2);
    }
}