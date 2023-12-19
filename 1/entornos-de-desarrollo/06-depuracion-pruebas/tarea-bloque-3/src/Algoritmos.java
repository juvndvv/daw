public class Algoritmos {
    public static double calculaArea(int base, int altura, String figura) throws IllegalArgumentException {
        if (figura.equals("triangulo")) {
            return (base * altura) / 2.0;        // Modificacion: casteado a double el divisor

        } else if (figura.equals("rectangulo")) {
            return base * altura;

        } else {
            throw new IllegalArgumentException("Figura incorrecta. Solo se acepta 'triangulo' o 'rectangulo'.");
        }
    }

    public static double resuelveEcuacion(int a, int b, int c, String tipo) throws IllegalArgumentException {
        // Modificacion: se ha implementado primero la validacion de la entrada
        if (!tipo.equals("sup") && !tipo.equals("inf")) {
            throw new IllegalArgumentException("Valor inválido. Se esperaba 'sup' o 'inf'.");
        }

        float raiz = b * b - 4 * a * c;

        if (raiz < 0) {
            throw new IllegalArgumentException("Raíz negativa. La ecuación no se puede resolver.");
        }

        if (tipo.equals("sup")) {
            return (-b + Math.sqrt(raiz)) / (2 * a);

        } else {
            return (-b - Math.sqrt(raiz)) / (2 * a);
        }
    }

}
