import java.util.Scanner;

public class Circulo {
    
    private double radio;

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

    public static void prueba() {
        Scanner sc = new Scanner(System.in);

        Circulo circulo = new Circulo();

        System.out.print("Introduce la longitud del radio: ");
        String entrada = sc.nextLine();

        while (!Utils.isNumeric(entrada)) {
            System.out.print("Introduce la longitud del radio: ");
            entrada = sc.nextLine();
        }
        
        double radio = Double.parseDouble(entrada);
        circulo.setRadio(radio);

        System.out.printf("Perimetro: %.2f%n", circulo.perimetro());
        System.out.printf("Area: %.2f%n", circulo.area());
    }
}
