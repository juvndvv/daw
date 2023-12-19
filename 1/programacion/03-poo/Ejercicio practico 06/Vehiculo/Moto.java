package Vehiculo;

public class Moto extends VehiculoTierra {    
    private boolean parabrisas;

    public Moto(String modelo, String matricula, int ruedas, boolean parabrisas) throws IllegalArgumentException {
        super(modelo, matricula, ruedas);
        
        if (ruedas != 2 && ruedas != 3)
            throw new IllegalArgumentException("Solo puede tener 2 o 3 ruedas");
        
        if (!matriculaValida())
            throw new IllegalArgumentException("La matricula debe contener 4 numeros y 3 letras");
        
        this.parabrisas = parabrisas;
    }

    public boolean getParabrisas() {
        return parabrisas;
    }

    public void setParabrisas(boolean parabrisas) {
        this.parabrisas = parabrisas;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.printf("[Parabrisas] %s%n", (parabrisas) ? "Si" : "No");
    }

    @Override
    protected boolean matriculaValida() {
        return matricula.matches(TipoMatricula.MOTO);
    }
}
