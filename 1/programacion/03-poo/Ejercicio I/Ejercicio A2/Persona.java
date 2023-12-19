public class Persona {
    
    private String dni;
    private String nombre;
    private String apellidos;
    private int edad;

    /* Constructores */
    public Persona(String dni, String nombre, String apellidos, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /* Metodos publicos */
    public void imprime() {
        System.out.println("DNI: " + this.dni);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Apellidos: " + this.apellidos);
        System.out.println("Edad: " + this.edad);
    }

    public boolean esMayorEdad() {
        return this.edad > 17;
    }

    public boolean esJubilado() {
        return this.edad > 64;
    }

    public int diferenciaEdad(Persona p) {
        return Math.abs(this.edad - p.edad);
    }
}
