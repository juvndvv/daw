package Ejercicio2;

import java.util.ArrayList;
import java.util.Scanner;

public class Alumno extends Persona {
    
    public int numeroAlumno;
    public ArrayList<Integer> notas;

    public Alumno(String nombre, int edad, int numeroAlumno) {
        super(nombre, edad);

        this.numeroAlumno = numeroAlumno;
        notas = new ArrayList<Integer>();
    }

    public int getNumeroAlumno() {
        return numeroAlumno;
    }

    public void setNumeroAlumno(int numeroAlumno) {
        this.numeroAlumno = numeroAlumno;
    }

    public ArrayList<Integer> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Integer> notas) {
        this.notas = notas;
    }

    public float getNotaMedia() {
        int sumatorio = this.notas.stream()
            .mapToInt(Integer::valueOf)
            .sum();

        int numNotas = this.notas.size();

        return sumatorio / numNotas;
    }

    public void insertarNotas() {
        int evaluacion = 1;

        while (evaluacion <= 3) {
            boolean insertado = insertarNota(evaluacion);
            if (!insertado)
                System.out.print("ERROR: La nota no es valida. ");
            else
                evaluacion++;
        }
    }

    private boolean insertarNota(int evaluacion) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Introduce la nota del alumno %s de la %dº evaluacion: ", this.nombre, evaluacion);
        String entrada = sc.nextLine();

        if (!esNumerico(entrada))
            return false;
        
        if (!esNotaValida(entrada))
            return false;

        this.notas.add(Integer.parseInt(entrada));

        return true;
    }

    private static boolean esNumerico(String str) {
        return str.matches("\\d+");
    }

    private static boolean esNotaValida(String str) {
        int nota = Integer.parseInt(str);
        return nota >= 0 && nota <= 10;
    }

    public static void main(String[] args) {
        Alumno a = new Alumno("Juanda", 23, 1);
        a.insertarNotas();
        System.out.println(a.getNotaMedia());
    }
}
