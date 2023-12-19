
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

    public void setNombre(String nombre) throws IllegalArgumentException{
        if (nombre.length() < 3)
            throw new IllegalArgumentException("El nombre de un gato debe tener al menos 3 caracteres: " + nombre);

        this.nombre = nombre;
    }

    public void setEdad(int edad) throws IllegalArgumentException {
        if (edad < 0)
            throw new IllegalArgumentException("La edad del gato no puede ser negativa: " + edad);

        this.edad = edad;
    }

    public void imprimir() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}