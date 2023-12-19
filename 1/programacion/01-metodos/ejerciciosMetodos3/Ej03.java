package practicas.ejerciciosMetodos3;

public class Ej03 {

    public static void muestraCalendario(int numeroDias, int diaInicio) {
        System.out.println("Lunes\t\tMartes\t\tMiercoles\tJueves\t\tViernes\t\tSabado\t\tDomingo");

        // se colocan espacios hasta el primer dia
        int i = 1;
        while (i != diaInicio) {
            System.out.print("\t\t");
            i++;
        }

        // se imprimen los dias
        for (int dia = 1; dia <= numeroDias; dia++, i++) {
            if (i == 8) {
                System.out.println();
                i = 1;
            }
            System.out.printf("%d\t\t", dia);
        }
        System.out.println();
    }
}
