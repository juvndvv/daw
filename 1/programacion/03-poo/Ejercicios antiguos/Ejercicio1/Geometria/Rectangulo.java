package Geometria;

public class Rectangulo {
    // Constantes
    static final float PI = 3.1416f;

    // Variables
    private Punto a;
    private Punto b;
    // Descripcion
    public int numRectangulos;
    private String nombre;
    // private String nombreFigura = "Rectangulo";

    // Constructores
    public Rectangulo() {
        this.a = new Punto();
        this.b = new Punto(1, 1);
    }

    public Rectangulo(double x1, double y1, double x2, double y2) {
        this.a = new Punto(x1, y1);
        this.b = new Punto(x2, y2);
    }

    public Rectangulo(double base, double altura) {
        this.a = new Punto();
        this.b = new Punto(base, altura);
    }

    public Rectangulo(Rectangulo other) {
        this.a = other.a;
        this.b = other.b;
    }

    // Setters y getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumRectangulos() {
        return numRectangulos;
    }

    public Punto getA() {
        return this.a;
    }

    public Punto getB() {
        return this.b;
    }

    public void setA(Punto a) {
        this.a = a;
    }

    public void setB(Punto b) {
        this.b = b;
    }

    // Metodos de clase
    public double calcularSuperficie() {
        Punto tercerVertice = new Punto(this.a.getX(), this.b.getY());
        double base = Punto.calcularDistancia(this.a, tercerVertice);
        double altura = Punto.calcularDistancia(this.b, tercerVertice);
        return base * altura;
    }

    public double calcularPerimetro() {
        Punto tercerVertice = new Punto(this.a.getX(), this.b.getY());
        double base = Punto.calcularDistancia(this.a, tercerVertice);
        double altura = Punto.calcularDistancia(this.b, tercerVertice);
        return (base + altura) * 2;
    }

    public void desplazar(double x, double y) {
        this.a.desplazar(x);
        this.b.desplazar(y);
    }
}
