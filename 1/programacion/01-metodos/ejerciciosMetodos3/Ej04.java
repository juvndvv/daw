package practicas.ejerciciosMetodos3;

public class Ej04 {

    public static String obtenerDomingoRamos(int year) {
        // Calculamos los valores de a, b, c y d
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + 24) % 30;
    
        // Calculamos el día del Domingo de Ramos
        int domingoRamos = 15 + d + (2 * b + 4 * c + 6 * d + 5) % 7;
    
        // Si el día del Domingo de Ramos es mayor que 31, entonces significa que
        // este cae en el mes de abril
        if (domingoRamos > 31) {
            return "Abril " + (domingoRamos - 31);
        }
        // De lo contrario, el Domingo de Ramos cae en el mes de marzo
        else {
            return "Marzo " + domingoRamos;
        }
    }
}
