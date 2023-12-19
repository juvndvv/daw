package practicas.ejerciciosMetodos3;

public class Ej02 {

    public static boolean sonAmigos(int a, int b) {
        return sumaDivisores(a) == b && sumaDivisores(b) == a;
    }

    public static boolean esDivisor(int divisor, int cociente) {
        return divisor % cociente == 0;
    }

    public static int sumaDivisores(int numero) {
        int suma = 0;
        for (int i = 1; i < numero; i++) {
            if (esDivisor(numero, i))
                suma += i;
        }
        return suma;
    }
}
