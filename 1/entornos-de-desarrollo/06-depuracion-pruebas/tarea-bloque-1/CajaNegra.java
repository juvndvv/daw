package ed.ud6.debugger.test;

import java.util.Random;
import java.util.logging.Logger;

public class CajaNegra {
	static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static int euclides(int a, int b) {
		if (b == 0) {
			return a;
		}
		return euclides(b, a % b);
	}

	public static int mcd(int a, int b) {
		LOG.info("Se va a calcular el máximo común divisor entre "
				+ a + " y " + b);
		if (a < 0 || b < 0) {
			LOG.severe("Solo se admiten numeros positivos");
			return -1;
		}

		return euclides(a, b);
	}

	public static int mcm(int a, int b) {
		LOG.info("Se va a calcular el mínimo común múĺtiplo entre "
				+ a + " y " + b);
		if (a < 0 || b < 0) {
			LOG.severe("Solo se admiten numeros positivos");
			return -1;
		}
		return (a / euclides(a, b)) * b;
	}

	public static int[][] matrixId(int n) {
		LOG.info("Se va a crear la matriz identidad de tamaño " + n);
		int[][] matrixOut = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r == c) {
					matrixOut[r][c] = 1;
				} else {
					matrixOut[r][c] = 0;
				}
			}
		}
		return matrixOut;
	}

	public static void printMatrix(int[][] matrix) {
		LOG.info("Se va a imprimir una matriz 2D de tamaño " + matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public static int[][] matrixRandom(int n, int seed) {
		LOG.info("Se va a crear una matriz aleatoria de tamaño " + n);
		if (n > 10) {
			LOG.warning("La matriz tiene un tamaño mayor que 10, n=" + n);
		}
		int[][] matrixOut = new int[n][n];
		Random rn = new Random(seed);
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				matrixOut[r][c] = rn.nextInt(9);
			}
		}
		return matrixOut;
	}

	public static int[][] matrixTranspose(int[][] matrix) {
		LOG.info("Se va a hacer la trasposición de la matriz de tamaño " + matrix.length);
		int[][] matrixOut = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrixOut[j][i] = matrix[i][j];
			}
		}
		return matrixOut;
	}

	public static double determinante(int[][] matrix) {
		LOG.info("Se va a calcular el determinante de la matriz de tamaño " + matrix.length);
		double det = 0.0;

		int signo = 1;

		if (matrix.length == 1) {
			LOG.warning("Matriz de tamaño 1. CASO BASE");
			return matrix[0][0];
		}

		for (int c = 0; c < matrix.length; c++) {
			// Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
			int[][] submatrix = getSubmatrix(matrix, matrix.length, c);
			det = det + signo * matrix[0][c] * determinante(submatrix);
			LOG.info("Calculada submatriz. Invertimos signo...");
			signo *= -1;
		}
		return det;
	}

	public static int[][] getSubmatrix(int[][] matrix, int n, int c) {
		LOG.info("Generamos submatriz de tamaño " + n + " en la columna " + c);
		int[][] submatrix = new int[n - 1][n - 1];
		int cont = 0;

		for (int j = 0; j < n; j++) {
			if (j == c)
				continue;
			for (int i = 1; i < n; i++)
				submatrix[i - 1][cont] = matrix[i][j];
			cont++;
		}
		return submatrix;
	}
}
