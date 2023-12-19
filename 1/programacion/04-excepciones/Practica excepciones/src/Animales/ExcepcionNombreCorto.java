package Animales;

public class ExcepcionNombreCorto extends IllegalArgumentException {
    public ExcepcionNombreCorto(String msg) {
        super(msg);
    }
}
