package Ejercicio2;

import java.util.ArrayList;
import java.util.Arrays;

public class Curso {
    
    public static void main(String[] args) {
        // Vamos a crear los alumnos
        Alumno a1 = new Alumno("Juan Daniel Forner", 23, 1);
        Alumno a2 = new Alumno("Isaac Serban", 20, 2);
        Alumno a3 = new Alumno("Victor Manuel", 53, 3);
        Alumno a4 = new Alumno("Alberto Tello", 52, 4);

        // Creamos oyentes
        Oyente o1 = new Oyente("Oyente 1", 1, 20);
        Oyente o2 = new Oyente("Oyente 2", 2, 10);
        Oyente o3 = new Oyente("Oyente 3", 3, 30);
        Oyente o4 = new Oyente("Oyente 3", 4, 40);
        
        // Creamos el ArrayList de alumnos
        Persona[] alumnosArray = new Persona[] {a1, a2, o1, a3, o2, o3, a4};
        ArrayList<Persona> personas = new ArrayList<Persona>(Arrays.asList(alumnosArray));

        // Ponemos las notas medias de todos los alumnos
        personas.stream()
            .filter(persona -> persona.getClass().getName().equals("Ejercicio2.Alumno"))
            .forEach((persona) -> {
                Alumno alumno = (Alumno) persona;
                alumno.insertarNotas();
            });
        
        // Mostramos las notas medias de cada alumno
        personas.stream()
            .filter(persona -> persona.getClass().getName().equals("Ejercicio2.Alumno"))
            .forEach((persona) -> {
                Alumno a = (Alumno) persona;
                System.out.printf("La nota media de %s: %.2f%n", a.getNombre(), a.getNotaMedia());
            });

        // Enseñamos los datos de los oyentes
        personas.stream()
            .filter(persona -> persona.getClass().getName().equals("Ejercicio2.Oyente"))
            .forEach((persona) -> {
                Oyente oyente = (Oyente) persona;
                System.out.println(oyente);
            });
    }
}
