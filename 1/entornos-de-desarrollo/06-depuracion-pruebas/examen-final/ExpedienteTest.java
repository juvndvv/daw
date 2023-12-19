import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class ExpedienteTest {
    private Expediente expediente;

    @BeforeEach
    void setup() {
        expediente = new Expediente(69);
    }

    @Test
    @DisplayName("testeando mejornota con calificaciones registradas")
    void mejorNotaTestValid() {
        expediente.addCalificacion(0);
        expediente.addCalificacion(5);
        expediente.addCalificacion(10);

        double expected = 10;

        double result = expediente.mejorNota();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando mejornota sin calificaciones registradas")
    void mejorNotaTestInvalid() {
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () -> expediente.mejorNota());

        String expectedMsg = "No hay calificaciones registradas";

        String resultMsg = e.getMessage();

        Assertions.assertEquals(expectedMsg, resultMsg);
    }

    @Test
    @DisplayName("testeando aprobados sin calificaciones")
    void aprobadosTestVacio() {
        int expected = 0;

        int result = expediente.aprobados();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando aprobados con calificaciones aprobadas")
    void aprobadosTestTodasAprobadas() {
        expediente.addCalificacion(5);
        expediente.addCalificacion(5);
        expediente.addCalificacion(10);
        expediente.addCalificacion(7.6);

        int expected = 4;

        int result = expediente.aprobados();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando aprobados con calificaciones suspendidas")
    void aprobadosTestTodasSuspendidas() {
        expediente.addCalificacion(0);
        expediente.addCalificacion(0);
        expediente.addCalificacion(4.99);
        expediente.addCalificacion(3.7);

        int expected = 0;

        int result = expediente.aprobados();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando aprobados con notas mixtas")
    void aprobadosTestNotasMixtas() {
        expediente.addCalificacion(3.4);
        expediente.addCalificacion(4.9);
        expediente.addCalificacion(10);
        expediente.addCalificacion(5);
        expediente.addCalificacion(0);
        expediente.addCalificacion(7.2);

        int expected = 3;

        int result = expediente.aprobados();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando aprobados con notas mixtas 2")
    void aprobadosTestNotasMixtas2() {
        expediente.addCalificacion(4.5);
        expediente.addCalificacion(1.2);
        expediente.addCalificacion(9.6);
        expediente.addCalificacion(5.8);
        expediente.addCalificacion(8.1);
        expediente.addCalificacion(6.75);

        int expected = 4;

        int result = expediente.aprobados();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando addCalificacion con nota minima")
    void addCalificacionNotaMinima() {
        boolean result = expediente.addCalificacion(0);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("testeando addCalificacion maxima")
    void addCalificacionNotaMaxima() {
        boolean result = expediente.addCalificacion(10);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("testeando addCalificacion notas intermedias")
    void addCalificacionNotaIntermedia() {
        boolean result = expediente.addCalificacion(4);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("testeando addCalificacion fuera de rango inferior")
    void addCalificacionTestNotaFueraInf() {
        boolean result = expediente.addCalificacion(-0.01);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("testeando addCalificacion fuera de rango superior")
    void addCalificacionTestNotaFueraSup() {
        boolean result = expediente.addCalificacion(10.01);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("testeando getCalificaciones con calificaciones registradas")
    void getCalificacionesTestValid() {
        expediente.addCalificacion(0);
        expediente.addCalificacion(1);
        expediente.addCalificacion(7);

        List<Double> expected = new ArrayList<>(List.of(0., 1., 7.));

        List<Double> result = expediente.getCalificaciones();

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("testeando getCalificaciones sin calificaciones registradas")
    void getCalificacionesTestInvalid() {
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () ->expediente.getCalificaciones());

        String expectedMsg = "No hay calificaciones registradas";

        String resultMsg = e.getMessage();

        Assertions.assertEquals(expectedMsg, resultMsg);
    }

    @Test
    @DisplayName("testeando getIdAlumno")
    void getIdAlumnoTest() {
        int expected = 69;

        int result = expediente.getIdAlumno();

        Assertions.assertEquals(expected, result);
    }
}
