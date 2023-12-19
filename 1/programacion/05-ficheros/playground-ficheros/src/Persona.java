import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private int edad;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;

    public Persona(String nombre, int edad, String dni, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    public Persona(String nombre, int edad, String dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.direccion = "";
        this.telefono = "";
        this.email = "";
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //toString
    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + ", dni=" + dni + ", direccion=" + direccion
                + ", telefono=" + telefono + ", email=" + email + "]";
    }
}
