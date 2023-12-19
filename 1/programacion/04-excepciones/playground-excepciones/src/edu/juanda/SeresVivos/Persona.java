package edu.juanda.SeresVivos;

public class Persona {
    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String nombre, String apellido, int edad) throws EdadInvalidaException {
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) throws EdadInvalidaException {
        validarEdad(edad);
        this.edad = edad;
    }

    private void validarEdad(int edad) throws EdadInvalidaException {
        if (edad <= 0)
            throw new EdadInvalidaException("La edad debe ser mayor que cero");
    }
}
