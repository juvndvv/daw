import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class AlgoritmosTest {
    @Test
    @DisplayName("Testeando calcularArea tipo triangulo con inputs validos")
    void testCalcularArea_ValidInputsTriangulo() {
        int base = 3;
        int altura = 5;
        String tipo = "triangulo";

        double expected = 7.5;
        double result = Algoritmos.calculaArea(base, altura, tipo);

        Assertions.assertEquals(expected, result, "El area de un triangulo de base 3 y altura 5 es 7.5");
    }

    @Test
    @DisplayName("Testeando calcularArea tipo rectangulo con inputs validos")
    void testCalcularArea_ValidInputsRectangulo() {
        int base = 3;
        int altura = 5;
        String tipo = "rectangulo";
        double expected = 15;

        double result = Algoritmos.calculaArea(base, altura, tipo);

        Assertions.assertEquals(expected, result, "El area de un rectangulo de base 3 y altura 5 es 15");
    }

    @Test
    @DisplayName("Testeando calcularArea con figura invalida")
    void testCalcularArea_InvalidInputs() {
        int base = 1;
        int altura = 1;
        String figura = "circulo";

        Assertions.assertThrows(IllegalArgumentException.class, () -> Algoritmos.calculaArea(base, altura, figura));
    }

    @Test
    @DisplayName("Testeando resuelveEcuacion con inputs validos (tipo: sup)")
    public void testResuelveEcuacion_ValidInputSup() {
        int a = 1;
        int b = 4;
        int c = 3;
        String tipo = "sup";
        double expected = -1.0;

        double result = Algoritmos.resuelveEcuacion(a, b, c, tipo);

        Assertions.assertEquals(expected, result, "La solucion superior de la ecuacion de segundo grado con a=1, b=4, c=3 es -1");
    }

    @Test
    @DisplayName("Testeando resuelveEcuacion con inputs validos (tipo: inf)")
    public void testResuelveEcuacion_ValidInputInf() {
        int a = 1;
        int b = 4;
        int c = 3;
        String tipo = "inf";
        double expected = -3.0;

        double result = Algoritmos.resuelveEcuacion(a, b, c, tipo);

        Assertions.assertEquals(expected, result, "La solucion inferior de la ecuacion de segundo grado con a=1, b=4, c=3 es -3");
    }

    @Test
    @DisplayName("Testeando resuelveEcuacion con tipo invalido")
    public void testResuelveEcuacion_InvalidTipo() {
        int a = 1;
        int b = 4;
        int c = 3;
        String tipo = "invalid";
        String expectedMessage = "Valor inválido. Se esperaba 'sup' o 'inf'.";

        IllegalArgumentException excepcion = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Algoritmos.resuelveEcuacion(a, b, c, tipo);
        });

        String mensajeError = excepcion.getMessage();
        Assertions.assertEquals(expectedMessage, mensajeError);
    }

    @Test
    @DisplayName("Testeando resuelveEcuacion con raiz negativa sup")
    public void testResuelveEcuacion_NegativeRaizSup() {
        int a = 1;
        int b = 1;
        int c = 1;
        String tipo = "sup";
        String expectedMessage = "Raíz negativa. La ecuación no se puede resolver.";

        IllegalArgumentException excepcion = Assertions.assertThrows(IllegalArgumentException.class, () ->
            Algoritmos.resuelveEcuacion(a, b, c, tipo)
        );

        String mensajeError = excepcion.getMessage();
        Assertions.assertEquals(expectedMessage, mensajeError);
    }

    @Test
    @DisplayName("Testeando resuelveEcuacion con raiz negativa inf")
    public void testResuelveEcuacion_NegativeRaizInf() {
        int a = 1;
        int b = 1;
        int c = 1;
        String tipo = "inf";
        String expectedMessage = "Raíz negativa. La ecuación no se puede resolver.";

        IllegalArgumentException excepcion = Assertions.assertThrows(IllegalArgumentException.class, () ->
            Algoritmos.resuelveEcuacion(a, b, c, tipo)
        );

        String mensajeError = excepcion.getMessage();
        Assertions.assertEquals(expectedMessage, mensajeError);
    }
}
