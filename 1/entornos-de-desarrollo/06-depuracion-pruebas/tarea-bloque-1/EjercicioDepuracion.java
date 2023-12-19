package ed.ud6.debugger.test;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class EjercicioDepuracion {
	static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void menu() {
		LOG.info("Mostramos el menú");

		System.out.println("Selecciona una opción");
		System.out.println("0) Salir");
		System.out.println("1) Hacer mates simples");
		System.out.println("2) Jugar con matrices");
		System.out.println("3) Cambia el nivel del logger");
		System.out.println("4) Cambia el nombre del archivo");
		System.out.println("Opcion: ");
	}

	public static void menuLogger() {
        LOG.info("Mostramos el menu del nivel de logger");

        System.out.println("Selecciona una opcion");
        System.out.println("1) INFO");
        System.out.println("2) WARNING");
    }

	public static String obtieneNombre() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce el nombre del archivo: ");
		String s = sc.nextLine();

		LOG.info("Nombre de archivo introducido: " + s);
		return s;
	}

	public static void main(String[] args) throws IOException {
		// Inicializamos nuestro logger
		try {
			MyLogger.setup();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems with creating log files");
		}
		
		LOG.info("Iniciamos la aplicación");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int opcion = -1;
		LOG.info("Var opcion = " + opcion);
		while (opcion != 0) {
			menu();
			opcion = sc.nextInt();
			LOG.info("Var opcion = " + opcion);
			switch (opcion) {
				case 1 -> {
					System.out.println("Introduce un número: ");
					int a = sc.nextInt();
					LOG.info("Var a = " + a);
					System.out.println("Introduce otro número: ");
					int b = sc.nextInt();
					LOG.info("Var b = " + b);
					int resultado = CajaNegra.mcd(a, b);
					LOG.info("Var resultado = " + resultado);
					if (resultado >= 1) {
						System.out.println("El maximo común divisor entre a y b es: " + resultado);
					}
					resultado = CajaNegra.mcm(a, b);
					if (resultado >= 1) {
						System.out.println("El minimo común múltiplo entre a y b es: " + resultado);
					}
				}
				case 2 -> {
					System.out.println("Introduce un tamaño N (entre 2 y 10): ");
					int n = sc.nextInt();
					LOG.info("Creamos matriz identidad de tamaño N:" + n);
					int[][] matrix = CajaNegra.matrixId(n);
					System.out.println("La matriz identidad de tamaño N es:");
					CajaNegra.printMatrix(matrix);
					System.out.println("Introduce una semilla: ");
					int semilla = sc.nextInt();
					LOG.info("Creamos matriz aleatoria de tamaño N:" + n + " y semilla: " + semilla);
					matrix = CajaNegra.matrixRandom(n, semilla);
					System.out.println("La matriz aleatoria es:");
					CajaNegra.printMatrix(matrix);
					LOG.info("Creamos matriz traspuesta");
					int[][] matrixTrans = CajaNegra.matrixTranspose(matrix);
					System.out.println("La matriz traspuesta es:");
					CajaNegra.printMatrix(matrixTrans);
					LOG.info("Calculamos el determinante de la matriz original");
					double detOrig = CajaNegra.determinante(matrix);
					System.out.println("El determinante de la matriz original es: " + detOrig);
					LOG.info("Calculamos el determinante de la matriz traspuesta");
					double detTrans = CajaNegra.determinante(matrixTrans);
					System.out.println("El determinante de la matriz traspuesta es: " + detTrans);
				}
				case 3 -> {
					menuLogger();
					int level = sc.nextInt() - 1;
					MyLogger.setLevel(level);
				}
				case 4 -> {
					String nuevoNombre = obtieneNombre();
					MyLogger.setFilename(nuevoNombre);
				}
				default -> {
				}
			}
		}
	}
}
