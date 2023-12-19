package edicion;

import java.io.FileWriter;

public class Articulo extends Edicion implements Vendible {
    private String autor;
    private String titulo;
    private int ingresos;

    public Articulo(String Isin, String fecha, String autor, String titulo) {
        super(Isin, fecha, "articulo");
        this.autor = autor;
        this.titulo = titulo;
        this.ingresos = 0;
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

    @Override
    public int getIngresos() {
        return ingresos;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public void vender() {
        this.ingresos += 10;
    }

    @Override
    public String info() {
        String info = super.info();
        return info + String.format("Autor: %s%nTitulo: %s%nIngresos: %d euros", this.autor, this.titulo, this.ingresos);
    }

    public void save(FileWriter writer) {

    }
}
