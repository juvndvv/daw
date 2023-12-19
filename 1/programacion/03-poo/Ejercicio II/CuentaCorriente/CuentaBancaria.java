package CuentaCorriente;

import java.util.ArrayList;

public class CuentaBancaria {

    private final String iban;
    private final String titular;
    private float saldo;
    private ArrayList<String> movimientos;

    /* Constructores */
    public CuentaBancaria(String iban, String titular) throws IllegalArgumentException{
        if (!ibanValido(iban))
            throw new IllegalArgumentException("ERROR: El IBAN no es valido");
        
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0;
        this.movimientos = new ArrayList<>();
    }

    /* Getters */
    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    /* Metodos */
    public void ingreso(float cantidad) throws IllegalArgumentException{
        if (cantidad <= 0)
            throw new IllegalArgumentException("ERROR: Solo admitido valores mayores a 0");
        
        if (cantidad > 3000)
            notificarHacienda();
        
        this.registrarMovimiento(cantidad, 1);

        this.saldo += cantidad;
    }

    public void retirada(float cantidad) throws IllegalArgumentException {
        if (this.saldo - cantidad < - 50)
            throw new IllegalArgumentException("ERROR: El saldo restante quedaria inferior a -50 euros");
        
        if (cantidad < 0)
            notificarNegativo();
        
        this.registrarMovimiento(cantidad, 0);

        this.saldo -= cantidad;
    }

    private void registrarMovimiento(float cantidad, int tipo) throws IllegalArgumentException{
        String simbolo;
        if (tipo == 0)
            simbolo = "-";  // retirada
        else if (tipo == 1)
            simbolo = "+";  // ingreso
        else
            throw new IllegalArgumentException("ERROR: Argumento tipo incorrecto");

        this.movimientos.add(
            String.format("%s%f", simbolo, cantidad)
        );
    }

    private static void notificarHacienda() {
        System.out.println("AVISO: Notificar a hacienda");
    }

    private static void notificarNegativo() {
        System.out.println("AVISO: Saldo negativo");
    }

    private static boolean ibanValido(String iban) {
        return iban.matches("[A-Z]{2}\\d{22}");
    }
}