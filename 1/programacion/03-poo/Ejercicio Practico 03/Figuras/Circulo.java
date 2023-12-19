public class Circulo {
    
    private double radio;

    public Circulo() {
        radio = 0;
    }

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return this.radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double perimetro() {
        return 2 * Math.PI * radio;
    }

    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }
}
