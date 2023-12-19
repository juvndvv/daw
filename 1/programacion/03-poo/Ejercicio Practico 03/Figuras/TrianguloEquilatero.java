public class TrianguloEquilatero  {
    
    private double base;
    private double altura;

    public TrianguloEquilatero() {
        this.base = 0;
        this.altura = 0;
    }

    public TrianguloEquilatero(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return this.base;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double perimetro() {
        return base * 3;
    }

    public double area() {
        return (base * altura) / 2;
    }
}
