package PackFecha;

public class DateFunctions {
    
    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));
    }

    public static boolean formatoOK(String fecha) {
        return fecha.matches("\\d{2}[-]\\d{2}[-]\\d{4}");
    }

    public static boolean esLogica(String fecha) {
        if (!formatoOK(fecha))
            return false;

        int dia = obtieneDia(fecha);
        int mes = obtieneMes(fecha);
        int anio = obtieneAnio(fecha);
        boolean esBisiesto = esBisiesto(anio);

        // comprobamos meses
        if (mes < 1 || mes > 12)
            return false;

        // comprobamos dias
        int[] mesesMasDias = { 1, 3, 5, 7, 9, 10, 12 };
        if (compruebaExistenciaEnArray(mesesMasDias, anio) && dia < 0 || dia > 31)
            return false;
        else if (mes != 2 && dia < 1 || dia > 30)
            return false;
        else if (esBisiesto && (dia < 1 || dia > 29))
            return false;
        else if (!esBisiesto && (dia < 1 || dia > 28))
            return false;

        return true;
    }

    private static int obtieneAnio(String fecha) {
        return Integer.parseInt(fecha.substring(6));
    }

    private static int obtieneMes(String fecha) {
        if (fecha.charAt(3) == '0') {
            return Integer.parseInt(fecha.substring(4, 5));
        }
        return Integer.parseInt(fecha.substring(3, 5));
    }

    private static int obtieneDia(String fecha) {
        if (fecha.charAt(0) == '0')
            return Integer.parseInt(fecha.substring(1, 2));
        return Integer.parseInt(fecha.substring(0, 2));
    }

    private static boolean compruebaExistenciaEnArray(int[] array, int valor) {
        int i = 0;
        while (i < array.length) {
            if (array[i] == valor)
                return true;
            else
                i++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(esBisiesto(2000));
    }
}