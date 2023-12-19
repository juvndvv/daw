/*
 * En el siguiente codigo se han realizado las siguientes tecnicas de refactorizacion:
 * 
 *      1. Extracción de método: se extrajo la lógica de la suma de los números en un método
 *      separado llamado sumar para reducir la complejidad del método main y mejorar la
 *      legibilidad del código.
 * 
 *      2. Eliminación de código duplicado: se eliminaron los dos bloques de código que realizaban
 *      la suma de dos números y se reemplazaron por una única llamada al método sumar.
 * 
 *      3. Uso de parámetros variables: se agregó un parámetro variable (int... numeros) en el método
 *      sumar para permitir la suma de cualquier cantidad de números.
 * 
 * TA. Composicion de metodos
 * TD. Simplificacion de llamadas a metodos
 */
class Ejemplo {
    public static int suma(int... numeros) {
        int suma = 0;
        for (int numero: numeros) {
            suma += numero;
        }
        return suma;
    }

    public static void main(String[] args) {
        int resultado1 = Ejemplo.suma(3, 5);
        int resultado2 = Ejemplo.suma(2, 4);
        int resultado3 = Ejemplo.suma(1, 2, 3);
        System.out.println("Suma 1: " + resultado1);
        System.out.println("Suma 2: " + resultado2);
        System.out.println("Suma 3: " + resultado3);
    }
}
