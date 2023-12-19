import java.util.Scanner;

public class TablaObj {
    
    private int tabla;
    private int multiplicaciones;

    public TablaObj(int tabla, int multiplicaciones) {
        this.tabla = tabla; 
        this.multiplicaciones = multiplicaciones;
    }

    public void mostrar() {
        for (int i = 1; i < multiplicaciones; i++) {
            System.out.printf("%d x %d = %d%n", i, this.tabla, i * this.tabla);
        }
    }

    public void preguntar() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < multiplicaciones; i++) {
            System.out.printf("%d x %d = ", i, this.tabla);
            
            int resultado = sc.nextInt();
            if (resultado != i * this.tabla)
                System.out.println("Incorrecto");
            else
                System.out.println("Correcto");
        }
    }

    public static void main(String[] args) {
        TablaObj tabla = new TablaObj(10, 11);
        tabla.preguntar();
    }
}
