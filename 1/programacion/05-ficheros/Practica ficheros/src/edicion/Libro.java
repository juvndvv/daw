package edicion;

public class Libro extends Edicion implements Prestable {
    private String autor;
    private String titulo;
    private boolean prestado;

    public Libro(String Isin, String fecha, String autor, String titulo, boolean prestado) {
        super(Isin, fecha, "libro");
        this.autor = autor;
        this.titulo = titulo;
        this.prestado = prestado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean prestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public void prestar() {
        this.prestado = true;        
    }

    @Override
    public void devolver() {
        this.prestado = false;
    }

    @Override
    public int getIngresos() {
        throw new UnsupportedOperationException("No se puede calcular ingresos para un libro");
    }

    @Override
    public String info() {
        String info = super.info();
        return info + String.format("Autor: %s%nTitulo: %s%nPrestado: %s", this.autor, this.titulo, (this.prestado) ? "Si" : "No");
    }
}