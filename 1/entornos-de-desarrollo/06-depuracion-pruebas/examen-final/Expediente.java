import java.util.ArrayList;
import java.util.List;

public class Expediente {
    private int idAlumno;
    private List<Double> calificaciones;

    public Expediente(int idAlumno) {
        this.idAlumno = idAlumno;
        this.calificaciones = new ArrayList<>();
    }

    public double mejorNota() {
        if (calificaciones.isEmpty()) {
                throw new IllegalStateException("No hay calificaciones registradas");
        }

        double mejor = 0.;    // Modificacion: se debe establecer en el minimo posible
        for (double calificacion : calificaciones) {
            if (calificacion > mejor) { // Modificacion: la calificacion actual ha de ser mayor que mejor
                mejor = calificacion;   // Modificacion: faltaba un punto y coma (;) :)
            }
        }
        return mejor;
    }

    public int aprobados() {
        int count = 0;
        for (double calificacion : calificaciones) {
            if (calificacion >= 5.0) {      // Modificacion: el 5 ha de estar comprendido tambien
                count++;
            }
        }
        return count;
    }

    public boolean addCalificacion(double calificacion) {
        if (calificacion >= 0.0 && calificacion <= 10.0) {      // Modificacion: los valores 0 y 10 tambien estan
            calificaciones.add(calificacion);                   //  comprendidos
            return true;
        } else {
            System.out.println("La calificación debe estar entre 0 y 10.");
            return false;
        }
    }

    public List<Double> getCalificaciones() {
        if (calificaciones.isEmpty()) {
            throw new IllegalStateException("No hay calificaciones registradas");
        }
        return calificaciones;
    }

    public int getIdAlumno() {
        return idAlumno;
    }
}

