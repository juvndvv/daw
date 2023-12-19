public class Monstruos {
    public void main(String[] args) {
        int numero = 4;
        int resultado = 0;
        for (int i = 0; i < numero; i++) {
            if (i % 2 == 0) {
                resultado += i * 2;
            } else {
                resultado += i * 3;
            }
        }
        if (resultado > 100) {
            System.out.println("El resultado es mayor que 100");
        } else if (resultado > 50) {
            System.out.println("El resultado es mayor que 50");
        } else {
            System.out.println("El resultado es menor o igual a 50");
        }
    }
}