/*
 * En el siguiente codigo se han realizado las siguientes tecnicas de refactorizacion:
 * 
 *      1. Extracción de métodos: se ha extraído el código que realiza los cálculos en el bucle 
 *      for en un método separado llamado calcularResultado. Esto hace que el método hacerAlgo 
 *      sea más corto y legible.
 * 
 *      2. Renombramiento de variables: se han renombrado las variables para darles nombres más
 *      descriptivos y significativos, lo que hace que el código sea más fácil de entender.
 * 
 *      3. Extracción de constantes: se ha extraído la constante 100 a una variable estática llamada
 *      LIMITE_ALTO y la constante 50 a una variable estática llamada LIMITE_BAJO.
 * 
 * En resumen, se han creado metodos que unicamente tienen una responsabilidad, cosa que hace que
 * el codigo sea mas legible y facilita el mantenimiento del mismo, y se han extraido constantes al
 * igual que se han renombrado variables para mejorar su legibilidad.
 * 
 * TA. Composicion de metodos
 * TC. Organizar los datos
 */

public class MonstruosRefactor {
    private static final int MULTIPLICADOR_PAR = 2;
    private static final int MULTIPLICADOR_IMPAR = 3;
    private static final int LIMITE_INFERIOR = 50;
    private static final int LIMITE_SUPERIOR = 100;

    public static void main(String[] args) {
        int numero = 4;
        int resultado = calcularResultado(numero);
        imprimirResultado(resultado);
    }

    public static int calcularResultado(int numero) {
        int resultado = 0;
        for (int i = 0; i < numero; i++) {
            if (esPar(i)) {
                resultado += i * MULTIPLICADOR_PAR;
            } else {
                resultado += i * MULTIPLICADOR_IMPAR;
            }
        }
        return resultado;
    }

    public static boolean esPar(int numero) {
        return numero % 2 == 0;
    }

    public static void imprimirResultado(int resultado) {
        if (resultado > LIMITE_SUPERIOR) {
            System.out.println("El resultado es mayor que " + LIMITE_SUPERIOR);
        } else if (resultado > LIMITE_INFERIOR) {
            System.out.println("El resultado es mayor que " + LIMITE_INFERIOR);
        } else {
            System.out.println("El resultado es menor o igual a " + LIMITE_INFERIOR);
        }
    }
}
