package CuadradoPuntos;

public class UsoCuadradoPuntos {
    public static void main(String args[]) {

        // Primer constructor
        CuadradoPuntos c1 = new CuadradoPuntos();
        CuadradoPuntos c2 = new CuadradoPuntos();

        c1.modificaValores(2, 2, 4, 4, 2);
        c2.modificaValores(1, 1, 5, 5, 4);

        System.out.println("El perímetro de c1 es :" + c1.calcularPerimetro());
        System.out.println("El área de c1 es :" + c1.calcularArea());
        System.out.println("El perímetro de c2 es :" + c2.calcularPerimetro());
        System.out.println("El área de c2 es :" + c2.calcularArea());

        // Segundo constructor
        CuadradoPuntos c3 = new CuadradoPuntos(2, 2, 4, 4, 2);

        System.out.println("El perímetro de c3 es :" + c3.calcularPerimetro());
        System.out.println("El área de c3 es :" + c3.calcularArea());

        // Tercer constructor
        CuadradoPuntos c4 = new CuadradoPuntos(c3);

        System.out.println("El perímetro de c4 es :" + c4.calcularPerimetro());
        System.out.println("El área de c4 es :" + c4.calcularArea());
    }
}
