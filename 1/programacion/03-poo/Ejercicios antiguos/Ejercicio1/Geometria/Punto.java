package Geometria;

public class Punto {
    private double x;
    private double y;

    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void desplazar(double a) {
        this.x += a;
        this.y += a;
    }

    public static double calcularDistancia(Punto a, Punto b) {
        return Math.abs(Math.sqrt(Math.pow(a.y - a.x, 2) + Math.pow(b.y - b.x, 2)));
    }
}
