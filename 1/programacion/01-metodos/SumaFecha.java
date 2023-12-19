public class SumaFecha {
    public static boolean estaEn(int[] array, int valor) {
        int i = 0;
        while (i < array.length) {
            if (array[i] == valor)
                return true;
            else i++;
        }
        return false;
    }

    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));
    }

    public static int obtieneAnio(String fecha) {
        return Integer.parseInt(fecha.substring(6));
    }

    public static int obtieneMes(String fecha) {
        if (fecha.charAt(3) == '0'){
            return Integer.parseInt(fecha.substring(4, 5));
        }
        return Integer.parseInt(fecha.substring(3, 5));
    }

    public static int obtieneDia(String fecha) {
        if (fecha.charAt(0) == '0')
            return Integer.parseInt(fecha.substring(1, 2));
        return Integer.parseInt(fecha.substring(0,2));
    }

    public static String sumaDia(String fecha) {
        int dia = obtieneDia(fecha);
        int mes = obtieneMes(fecha);
        int anio = obtieneAnio(fecha);
        boolean esBisiesto = esBisiesto(anio);

        // Comprobamos si se puede sumar al dia
        int[] mesesMasDias = {1, 3, 5, 7, 9, 10, 12};
        if (estaEn(mesesMasDias, mes) && dia < 31) {
            dia++;
        } else if (mes != 2 &&  dia < 30) {
            dia++;
        } else if (esBisiesto && dia < 29) {
            dia++;
        } else if (!esBisiesto && dia < 28) {
            dia++;

        // Si no se puede sumar al dia, establecemos el dia en 1 y sumamos al mes si es posible
        } else if (mes != 12) {
            dia = 1;
            mes++;

        // Si no se puede sumar el mes, establecemos el dia y el mes en 1 y sumamos el año
        } else {
            dia = 1;
            mes = 1;
            anio++;
        }

        return String.format("%02d-%02d-%04d", dia, mes, anio);
    }

    public static void main(String[] args) {
        String fecha = "01-01-2000";
        
        for (int i = 0; i < 370; i++) {
            System.out.println(fecha);
            fecha = sumaDia(fecha);
        } 
        
    }
}