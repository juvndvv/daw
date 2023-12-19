package VideoClub;

public class Alquiler {
    private Dvd dvd;
    private int tiempo; // dias

    public Alquiler(Dvd dvd, int tiempo) {
        this.dvd = dvd;
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public Dvd getDvd() {
        return dvd;
    }

    public float getPrecio() {
        if (dvd.getTipo() == Dvd.INFANTIL)
            return tiempo * 1.5f;
        
        else if (dvd.getTipo() == Dvd.NORMAL)
            return tiempo * 2f;
        
        return tiempo * 3f; // Dvd.NOVEDAD
    }

    public int getPuntos() {
        return tiempo + ((dvd.getTipo() == Dvd.NOVEDAD) ? 1 : 0);
    }

    protected String informe() {
        return String.format("Titulo: %s | Tiempo: %d dias | Clasificacion: %s%n", dvd.getTitulo(), tiempo, dvd.getTipo().toString());
    }
}
