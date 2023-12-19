package Vehiculo;

public class GloboAerostatico extends Vehiculo {
    private float envoltura;
    private int tiempoMaxEstimado;
    boolean conductor;

    public GloboAerostatico(String modelo, String matricula, float envoltura, int tiempoMaxEstimado, boolean conductor) {
        super(modelo, matricula);

        if (!matriculaValida())
            throw new IllegalArgumentException("La matricula debe contener 4 letras y 6 numeros");

        this.envoltura = envoltura;
        this.tiempoMaxEstimado = tiempoMaxEstimado;
        this.conductor = conductor;
    }

    public float getEnvoltura() {
        return envoltura;
    }

    public void setEnvoltura(float envoltura) {
        this.envoltura = envoltura;
    }

    public int getTiempoMaxEstimado() {
        return tiempoMaxEstimado;
    }

    public void setTiempoMaxEstimado(int tiempoMaxEstimado) {
        this.tiempoMaxEstimado = tiempoMaxEstimado;
    }

    public boolean getConductor() {
        return conductor;
    }

    public void setConductor(boolean conductor) {
        this.conductor = conductor;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.printf("[Envoltura] %.2fm3 [Tiempo estimado] %dh [Conductor] %s%n", envoltura, tiempoMaxEstimado, (conductor) ? "Si" : "No");
    }

    @Override
    protected boolean matriculaValida() {
        return matricula.matches(TipoMatricula.GLOBO_AEROSTATICO);
    }
}
