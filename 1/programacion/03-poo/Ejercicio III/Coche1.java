import java.util.Scanner;

public class Coche1 {
    
    private String matricula;
    private Color color;
    private float cilindrada;
    private int plazas;
    private String propietario;

    public Coche1() {

    }

    public Coche1(String propietario, Color color, float cilindrada, int plazas) {
        this.propietario = propietario;
        this.color = color;
        this.cilindrada = cilindrada;
        this.plazas = plazas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(float cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String toString() {
        return String.format(
            "Matricula: %s%n" +
            "Propietario: %s%n" +
            "Color: %s%n" +
            "Cilindrada %.2f%n" +
            "Plazas: %d%n",
            matricula, propietario, color.getName(), cilindrada, plazas
        );
    }

    public void visualiza() {
        System.out.printf(
            "Matricula: %s%n" +
            "Propietario: %s%n" +
            "Color: %s%n" +
            "Cilindrada %.2f%n" +
            "Plazas: %d%n",
            matricula, propietario, color.getName(), cilindrada, plazas
        );
    }

    public void pintar() {
        System.out.println("Selecciona el color:");
        Color.mostrarColores();
    }

    public static void main(String[] args) {
        Coche1 c = new Coche1("Juan Daniel Forner", Color.VERDE, 550, 2);

        c.visualiza();
        c.pintar();
    }
}