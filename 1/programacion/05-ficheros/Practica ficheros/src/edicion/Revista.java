package edicion;

public class Revista extends Edicion implements Vendible {

    private String nombre;
    private int ingresos;

    public Revista(String Isin, String fecha, String nombre) {
        super(Isin, fecha, "revista");
        this.nombre = nombre;
        this.ingresos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        this.ingresos += 3;
    }

    @Override
    public String info() {
        String info = super.info();
        return info + String .format("Nombre: %s%nIngresos: %d euros", this.nombre, this.ingresos);
    }
}