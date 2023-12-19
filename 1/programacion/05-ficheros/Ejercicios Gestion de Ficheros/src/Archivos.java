public class Archivos {
    // TODO convertir todo a objetos de tipo File
    public static final String FILE_LOCATION = "saves/";
    public static final String NUMEROS = "numeros.txt";
    public static final String NOTAS_ALUMNOS = "alumnos_notas.txt";
    public static final String NOMBRES = "usa_nombres.txt";
    public static final String APELLIDOS = "usa_apellidos.txt";

    public static String getNombres() {
        return FILE_LOCATION + NOMBRES;
    }

    public static String getApellidos() {
        return FILE_LOCATION + APELLIDOS;
    }

    public static String getNumeros() {
        return FILE_LOCATION + NUMEROS;
    }

    public static String getNotasAlumnos() {
        return FILE_LOCATION + NOTAS_ALUMNOS;
    }

}
