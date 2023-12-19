import java.util.Scanner;

public class PrimoObj {

    private int numero;

    public PrimoObj() {
        this.numero = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean esPrimo() {
        boolean primo = true;
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                primo = false;
            }
        }
        return primo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrimoObj numero = new PrimoObj();
        int num;
        do {
            System.out.print("Introduce un número: ");
            num = sc.nextInt();
            numero.setNumero(num);
            if (numero.esPrimo()) {
                System.out.println("El número " + numero.getNumero() + " es primo.");
            } else {
                System.out.println("El número " + numero.getNumero() + " no es primo.");
            }
        } while (num != 0);
    }
}
