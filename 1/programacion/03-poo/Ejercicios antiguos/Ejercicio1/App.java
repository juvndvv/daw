import Geometria.Rectangulo;

public class App {
    public static void main(String[] args) {
        // Declarando tres objetos Rectangulo
        Rectangulo r1, r2, r3;

        // Instanciando
        r1 = new Rectangulo();
        r2 = new Rectangulo(10, 10);
        r3 = new Rectangulo(0, 0, 10, 10);

        // Asignar valores a1 = 0, a2 = 0, b1 = 10, b2 = 10 a r1
        r1.getA().setX(0);
        r1.getA().setY(0);
        r1.getB().setX(10);
        r1.getB().setY(10);

        // Calcular area y perimetro y mostrarlo por pantalla
        System.out.println(r1.calcularSuperficie());
        System.out.println(r1.calcularPerimetro());

        System.out.println(r2.calcularSuperficie());
        System.out.println(r2.calcularPerimetro());

        System.out.println(r3.calcularSuperficie());
        System.out.println(r3.calcularPerimetro());
    }
}