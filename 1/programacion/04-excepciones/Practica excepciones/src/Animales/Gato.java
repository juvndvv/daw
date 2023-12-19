package Animales;

public class Gato {
    private String nombre;
    private int edad;

    public Gato(String nombre, int edad) throws IllegalArgumentException {
        setNombre(nombre);
        setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) throws ExcepcionNombreCorto{
        if (nombre.length() < 3)
            throw new ExcepcionNombreCorto("El nombre no puede contener menos de 3 caracteres");

        this.nombre = nombre;
    }

    public void setEdad(int edad) throws ExcepcionEdadNegativa {
        if (edad < 0)
            throw new ExcepcionEdadNegativa("La edad del gato no puede ser negativa");

        this.edad = edad;
    }

    public void imprimir() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}