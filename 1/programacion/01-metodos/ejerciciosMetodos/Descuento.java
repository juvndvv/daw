import java.util.Scanner;

public class Descuento {

    public static void main(String[] args) {
        System.out.print("Introduce el total de la factura: ");
        float factura = leePrecioValido();

        System.out.print("Introduce el precio pagado: ");
        float pagado = leePrecioValido();

        float descuento = calculaDescuento(factura, pagado);
        System.out.print(String.format("El descuento es de: %.2f", descuento) + '%');
    }
    
    public static float calculaDescuento(float precioTotal, float precioPagado) {
        return 100 - ((precioPagado * 100) / precioTotal);
    }

    public static float leePrecioValido() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (!entrada.matches("\\d*[[.][\\d*]+]+")) {
            System.out.println("Formato: [\\d*[.]\\d*]");
            return leePrecioValido();
        }

        return Float.parseFloat(entrada);
    }
}
