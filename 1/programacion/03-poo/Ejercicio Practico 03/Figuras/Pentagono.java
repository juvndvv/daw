public class Pentagono {
    
    private double lado;

    public Pentagono() {
        this.lado = 0;
    }

    public Pentagono(double lado) {
        this.lado = lado;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double perimetro() {
        return 5 * lado;
    }

    public double area() {
        return perimetro() * apotema() / 2;
    }

    private double apotema() {
        return this.lado / (2 * Math.tan(Math.toRadians(36)));
    }
}
