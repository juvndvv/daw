package Vehiculo;

public class Coche extends VehiculoTierra {
    private boolean aireAcondicionado;

    public Coche(String modelo, String matricula, int ruedas, boolean aireAcondicionado) {
        super(modelo, matricula, ruedas);
        
        if (ruedas != 3 && ruedas != 4)
            throw new IllegalArgumentException("Solo puede tener 3 o 4 ruedas");

        if (!matriculaValida())
            throw new IllegalArgumentException("La matricula debe contener 4 numeros y 3 letras");

        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public void imprimir() {
        super.imprimir();
        System.out.printf("[Aire acondicionado] %s%n", (aireAcondicionado) ? "Si" : "No");
    }

    @Override
    protected boolean matriculaValida() {
        return matricula.matches(TipoMatricula.COCHE);
    }
}