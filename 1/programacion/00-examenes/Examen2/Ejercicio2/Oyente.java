package Ejercicio2;

public class Oyente extends Persona {
    
    public int horas;

    public Oyente(String nombre, int edad, int horas) {
        super(nombre, edad);
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return String.format(
            "Nombre: %s%n" +
            "Edad: %d%n" +
            "Horas: %d",
            this.nombre, this.edad, this.horas
        );
    }
}
