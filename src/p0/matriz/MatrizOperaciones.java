package p0.matriz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MatrizOperaciones {

	private int matrix[][];

	public MatrizOperaciones(int n, int min, int max) {
		matrix = new int[n][n];
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = random.nextInt(max + 1 - min) + min;
			}
		}
	}

	public MatrizOperaciones(String nomFich) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(new File("files\\" + nomFich));
			br = new BufferedReader(fr);

			createMatrix(br);

		} catch (Exception e) {
			System.out.println("No se ha podido leer el fichero '" + nomFich + "'");
		} finally {

			try {
				fr.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar el fichero '" + nomFich + "'");
			}
		}
	}

	private void createMatrix(BufferedReader br) {
		int size = 0;
		String line = "";

		try {
			size = Integer.parseInt(br.readLine());
			matrix = new int[size][size];
			line = br.readLine();

			int row = size;
			while (line != null) {
				String[] rowStr = line.split("\t");
				for (int column = 0; column < size; column++) {
					matrix[row][column] = Integer.parseInt(rowStr[column]);
				}
				row++;
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error al crear la matriz");
		}
	}

	public int getTam() {
		return matrix.length;
	}

	public void escribir() {
		System.out.println("Matriz:");

		for (int i = 0; i < getTam(); i++) {
			StringBuilder linea = new StringBuilder();

			for (int j = 0; j < getTam(); j++) {
				linea.append(matrix[i][j]);
				linea.append(" ");
			}
			System.out.println(linea.toString());
		}
	}

	public int sumarDiagonal1() {
		int result = 0;

		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				if (i == j) {
					result += matrix[i][j];
				}
			}
		}

		return result;
	}

	public int sumarDiagonal2() {
		int result = 0;

		for (int i = 0; i < getTam(); i++) {
			result += matrix[i][i];
		}

		return result;
	}

	public void recorrerCamino(int i, int j) {
		while (i >= 0 && j >= 0 && i < getTam() && j < getTam() && matrix[i][j] != -1) {
			switch (matrix[i][j]) {
			case 1:
				matrix[i][j] = -1;
				i = i - 1;
			case 2:
				matrix[i][j] = -1;
				j = j + 1;
			case 3:
				matrix[i][j] = -1;
				i = i + 1;
			case 4:
				matrix[i][j] = -1;
				j = j - 1;
			}
		}
	}

}
