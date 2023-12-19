import java.util.Scanner;

public class Fecha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce una fecha: ");
        String fecha = sc.nextLine();
        

        if (esFechaValida(fecha))
            System.out.println("La fecha es valida");
        else
            System.out.println("La fecha no es valida");
    }

    public static boolean esFechaValida(String fecha) {
        // comprobamos el formato
        if (!fecha.matches("\\d{2}[-]\\d{2}[-]\\d{4}"))
            return false;
        
        int dia = obtieneDia(fecha);
        int mes = obtieneMes(fecha);
        int anio = obtieneAnio(fecha);

        // comprobamos meses
        if (mes < 1 || mes > 12)
            return false;

        // comprobamos dias
        int[] mesesMasDias = {1, 3, 5, 7, 9, 10, 12};
        if (compruebaExistenciaEnArray(mesesMasDias, anio) && dia < 1 || dia > 31)
            return false;
        else if (mes != 2 && dia < 1 || dia > 30)
            return false;
        else if (esAnioBisiesto(anio) && dia < 1 || dia > 29)
            return false;
        else if (dia < 1 || dia > 28)  
            return false;

        return true;
    }

    public static boolean esAnioBisiesto(int anio) {
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

    public static boolean compruebaExistenciaEnArray(int[] array, int valor) {
        int i = 0;
        while (i < array.length) {
            if (array[i] == valor)
                return true;
            else i++;
        }
        return false;
    }
}
