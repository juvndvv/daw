package edicion;

import java.io.Serializable;

public abstract class Edicion implements Serializable {
    protected String Isin;
    protected String fecha;
    protected String tipo;

    public Edicion(String Isin, String fecha, String tipo) {
        this.Isin = Isin;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public abstract int getIngresos();

    public String getIsin() {
        return Isin;
    }
    
    public void setIsin(String isin) {
        Isin = isin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String info() {
        return String.format("ISIN: %s%nFecha: %s%nTipo: %s%n", this.Isin, this.fecha, this.tipo);
    }   
}