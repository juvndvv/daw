package Vehiculo;

public abstract class VehiculoTierra extends Vehiculo {
    protected int ruedas;

    public VehiculoTierra(String modelo, String matricula, int ruedas) {
        super(modelo, matricula);
        this.ruedas = ruedas;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.printf("[Ruedas] %d ", ruedas);
    }

    @Override
    protected abstract boolean matriculaValida();
}