
// Modifica la clase UsoCuadradoPuntos para crear el array, llenarlo y muestra su área y color.

public class UsoCuadradoPuntos {
    public static float floatAleatorio() {
        return (float) (Math.random()*20)+1;
    }

    public static int intAleatorio() {
        return (int) (Math.random()*20)+1;
    }

    public static CuadradoPuntos cuadradoAleatorio() {
        return new CuadradoPuntos(floatAleatorio(), floatAleatorio(), floatAleatorio(), floatAleatorio(), intAleatorio());
    }

    public static void main(String args[]) {
        CuadradoPuntos[] v = new CuadradoPuntos[15];

        // Llenamos el array
        for (int i = 0; i < 15; i++) {
            v[i] = cuadradoAleatorio();
        }

        // Mostramos area y color
        for (CuadradoPuntos c: v) {
            System.out.printf("Area: %.2f%n", c.calcularArea());
            System.out.printf("Area: %d%n%n", c.getColor());
        }
    }
}
