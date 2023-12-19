import java.util.ArrayList;

public class Fundacion {

    public static void informe(ArrayList<Edicion> ediciones) {
        int ingresosTotales = 0;
        for (Edicion e: ediciones) {
            System.out.println("[INFO]");
            System.out.println(e.info());
            System.out.println();

            if (e instanceof Revista r) {
                ingresosTotales += r.getIngresos();
            } else if (e instanceof Articulo a) {
                ingresosTotales += a.getIngresos();
            }
        }

        System.out.println("Ingresos totales: " + ingresosTotales + " euros");
    }

    public static void main(String[] args) {
        ArrayList<Edicion> ediciones = new ArrayList<Edicion>();

        Revista r1 = new Revista("55656", "23-11-1999", "El Mundo");
        Libro l1 = new Libro("894536", "28-11-1947", "Juan Daniel Forner", "Mein Kampf", false);
        Articulo a1 = new Articulo("54653", "08-09-1999", "Jorge Garriga", "Como perder la cabeza");

        a1.vender();
        a1.vender();

        r1.vender();
        r1.vender();
        r1.vender();
        r1.vender();

        ediciones.add(r1);
        ediciones.add(l1);
        ediciones.add(a1);

        informe(ediciones);
    }
}
