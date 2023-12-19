package VideoClub;

public class Dvd {
    public final static String INFANTIL = "Infantil";
    public final static String NORMAL = "Normal";
    public final static String NOVEDAD = "Novedad";

    private String titulo;
    private String tipo;

    public Dvd(String titulo, String tipo) throws IllegalArgumentException {
        if (tipo != INFANTIL && tipo != NORMAL && tipo != NOVEDAD)
            throw new IllegalArgumentException("El tipo debe ser Infantil, Novedad o Normal");

        this.titulo = titulo;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
