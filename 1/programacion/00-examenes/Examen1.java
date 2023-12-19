import java.util.Scanner;

public class Examen1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int i = 0;
            int j = 0;

            // Estudiantes
            int nEstudiantes = 8;
            String[] nombreEstudiantes = new String[nEstudiantes];
            for (i = 0; i < nEstudiantes; i++) {
                System.out.printf("Introduce el nombre del estudiante %d: ", i + 1);
                nombreEstudiantes[i] = sc.nextLine();
            }

            // Soluciones
            System.out.print("Introduce el numero de preguntas del examen: ");
            int nPreguntas = leeNumeroPreguntas();

            char[] respuestasCorrectas = new char[nPreguntas];
            for (j = 0; j < nPreguntas; j++) {
                System.out.printf("Introduce la solucion de la pregunta %d: ", j + 1);
                respuestasCorrectas[j] = leeRespuesta();
            }

            char[][] respuestasEstudiantes = new char[8][nPreguntas];
            for (i = 0; i < nEstudiantes; i++) {
                System.out.printf("Respuestas de %s%n", nombreEstudiantes[i]);
                for (j = 0; j < nPreguntas; j++) {
                    respuestasEstudiantes[i][j] = leeRespuesta();
                }
            }

            int opcion;
            do {
                opcion = muestraMenuYLeeOpcion();
                switch (opcion) {

                    // Muestra la nota obtenida de cada estudiante. Esa nota corresponde
                    // al numero de aciertos que tuvo el estudiante / n de preguntas
                    case 1:
                        for (i = 0; i < nEstudiantes; i++) {
                            float nAciertos = 0;
                            for (j = 0; j < nPreguntas; j++) {
                                if (respuestasCorrectas[j] == respuestasEstudiantes[i][j]) {
                                    nAciertos++;
                                }
                            }
                            muestraAlumnoNota(nombreEstudiantes[i], nAciertos / nPreguntas);
                        }
                        break;

                    // Muestra la media de aciertos de los 8 alumnos
                    case 2:
                        float sumatorioAciertosTotales = 0;

                        for (i = 0; i < nEstudiantes; i++) {
                            float sumatorioAciertosAlumno = 0;
                            for (j = 0; j < nPreguntas; j++) {
                                if (respuestasCorrectas[j] == respuestasEstudiantes[i][j]) {
                                    sumatorioAciertosAlumno++;
                                }
                            }
                            sumatorioAciertosTotales += sumatorioAciertosAlumno;
                        }
                        System.out.printf("La media de acierto de los alumnos es de %f%n",
                                sumatorioAciertosTotales / nEstudiantes);
                        break;

                    // Dado el nombre de un alumno, muestra su nota
                    case 3:
                        // Pedimos alumno y buscamos hasta encontrarlo
                        System.out.print("Introduce el nombre del alumno: ");
                        String nombreBuscado = sc.nextLine();
                        i = buscaPosicionString(nombreEstudiantes, nombreBuscado);
                        while (i == -1) {
                            System.out.println("Alumno no encontrado");
                            System.out.print("Introduce el nombre del alumno: ");
                            nombreBuscado = sc.nextLine();
                            i = buscaPosicionString(nombreEstudiantes, nombreBuscado);
                        }

                        // Calcula y muestra la nota del alumno
                        float nAciertos = 0;
                        for (j = 0; j < nPreguntas; j++) {
                            if (respuestasCorrectas[j] == respuestasEstudiantes[i][j]) {
                                nAciertos++;
                            }
                        }
                        muestraAlumnoNota(nombreEstudiantes[i], nAciertos / nPreguntas);
                        break;
                    case 4:
                        System.out.println("Fin del programa");
                }
            } while (opcion != 4);
        }
    }

    public static void muestraAlumnoNota(String alumno, float nota) {
        System.out.printf("La nota de %s es %.2f/1.00%n", alumno, nota);
    }

    public static int muestraMenuYLeeOpcion() {
        System.out.println("1. Notas");
        System.out.println("2. Media");
        System.out.println("3. Nota alumno");
        System.out.println("4. Salir");

        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (!esEnteroPositivo(entrada)) {
            System.out.println("[error] No es un numero");
            entrada = Integer.toString(muestraMenuYLeeOpcion());
        }

        int opcion = Integer.parseInt(entrada);
        if (opcion < 1 || opcion > 4) {
            System.out.println("[error] Tiene que ser un numero entre 1 y 4");
            opcion = muestraMenuYLeeOpcion();
        }
        sc.close();
        return opcion;
    }

    public static int buscaPosicionString(String[] array, String c) {
        boolean encontrado = false;
        int i = 0;
        while (i < array.length && !encontrado) {
            if (array[i].equalsIgnoreCase(c))
                encontrado = true;
            else
                i++;
        }
        if (!encontrado)
            i = -1;
        return i;
    }

    public static boolean esEnteroPositivo(String c) {
        boolean esEntero = false;
        if (c.matches("[\\d]*")) {
            esEntero = true;
        }
        return esEntero;
    }

    public static int leeNumeroPreguntas() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (!esEnteroPositivo(entrada)) {
            System.out.println("[error] No es un numero entero positivo");
            entrada = Integer.toString(leeNumeroPreguntas());
        }

        int nPreguntas = Integer.parseInt(entrada);
        if (nPreguntas < 1 || nPreguntas > 20) {
            System.out.println("[error] Debe de ser un numero entre 1 y 20, ambos inclusive");
            nPreguntas = leeNumeroPreguntas();
        }
        sc.close();
        return nPreguntas;
    }

    public static char leeRespuesta() {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        if (!entrada.matches("[A-E]")) {
            System.out.println("[error] Debe ser un caracter entre A y E");
            entrada = String.valueOf(leeRespuesta());
        }
        sc.close();
        return entrada.charAt(0);
    }
}