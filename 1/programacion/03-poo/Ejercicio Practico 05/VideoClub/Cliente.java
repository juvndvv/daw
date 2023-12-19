package VideoClub;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private ArrayList<Alquiler> listaAlq = new ArrayList<Alquiler>();

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void nuevoAlquiler(Alquiler alquiler) {
        listaAlq.add(alquiler);
    }

    public String informe() {

        String informe = "";

        // Datos del cliente
        informe += String.format("Nombre: %s%nNumero alquileres: %d%n", nombre, listaAlq.size());

        // Dinero y puntos que se generan dependiendo del alquiler
        float dinero = 0;
        int puntos = 0;
        for (Alquiler a: listaAlq) {
            dinero += a.getPrecio();
            puntos += a.getPuntos();
        }

        informe += String.format("Adeudado: %.2f eur%nPuntos: %d", dinero, puntos);

        return informe;
    }
}