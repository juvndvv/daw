package Vehiculo;

public abstract class Vehiculo implements Imprimible {
    protected String modelo;
    protected String matricula;

    public Vehiculo(String modelo, String matricula) {
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void imprimir() {
        System.out.printf("[Modelo] %s [Matricula] %s ", modelo, matricula);
    }

    protected abstract boolean matriculaValida();
}