package Vehiculo;

public class Yate extends Vehiculo{
    private float eslora;
    private boolean motor;

    public Yate(String modelo, String matricula, float eslora, boolean motor) {
        super(modelo, matricula);

        if (!matriculaValida())
            throw new IllegalArgumentException("La matricula debe contener entre 6 y 9 numeros");

        this.eslora = eslora;
        this.motor = motor;
    }

    public float getEslora() {
        return eslora;
    }

    public void setEslora(float eslora) {
        this.eslora = eslora;
    }

    public boolean getMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.printf("[Eslora] %.2fm2 [Motor] %s%n", eslora, (motor) ? "Si" : "No");
    }

    @Override
    protected boolean matriculaValida() {
        return matricula.matches(TipoMatricula.YATE);
    }
}
