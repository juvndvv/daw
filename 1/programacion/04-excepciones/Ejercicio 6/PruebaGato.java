public class PruebaGato {
    public static void main(String[] args) {
        try {
            Gato gato1 = new Gato("Garfield", 5);
            Gato gato2 = new Gato("Tom", -1); // Lanza una excepción
            Gato gato3 = new Gato("Miau", 2);

            gato1.setEdad(7);
            gato2.setEdad(3); // Lanza una excepción
            gato3.setNombre("Minina");

            gato1.imprimir();
            gato2.imprimir(); // No se imprime debido a la excepción lanzada en el constructor
            gato3.imprimir();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}