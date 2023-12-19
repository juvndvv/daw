package Vehiculo;

public class TipoMatricula {
    public final static String COCHE = "(\\d{4}[a-z]{3})|(\\d{4}[A-Z]{3})";
    public final static String MOTO = "(\\d{4}[a-z]{3})|(\\d{4}[A-Z]{3})";
    public final static String YATE = "\\d{6,9}";
    public final static String GLOBO_AEROSTATICO = "([a-z]{4}\\d{6})|([A-Z]{4}\\d{6})";
}