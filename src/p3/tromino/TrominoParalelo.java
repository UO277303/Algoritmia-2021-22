package p3.tromino;

import java.util.concurrent.RecursiveAction;

public class TrominoParalelo extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	int[][] tablero;
	int count = 0;

	private int x;
	private int y;
	private int n;
	private int orig_x;
	private int orig_y;

	public TrominoParalelo(int x, int y, int n, int orig_x, int orig_y) {
		if (n > 0 && potenciaDe2(n) && x < n && y < n) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.orig_x = orig_x;
			this.orig_y = orig_y;
			this.tablero = new int[n][n];
			this.tablero[x][y] = -1;
		} else {
			throw new IllegalArgumentException("Error en los parámetros.");
		}
	}

	private boolean potenciaDe2(int x) {
		return Math.log(x) / Math.log(2) % 1 == 0;
	}

	@Override
	protected void compute() {
		int cuadrante = buscarCuadrante(orig_x + n / 2 - 1, orig_y + n / 2 - 1, x, y);

		// Centro del subtablero
		int[] c = { orig_x + n / 2 - 1, orig_y + n / 2 - 1 };

		count++;

		if (n > 2) {
			if (cuadrante == 1) {
				tablero[c[0]][c[1] + 1] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				TrominoParalelo rec1 = new TrominoParalelo(x, y, n / 2, orig_x, orig_y);
				TrominoParalelo rec2 = new TrominoParalelo(c[0], c[1] + 1, n / 2, orig_x, orig_y + n / 2);
				TrominoParalelo rec3 = new TrominoParalelo(c[0] + 1, c[1], n / 2, orig_x + n / 2, orig_y);
				TrominoParalelo rec4 = new TrominoParalelo(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

				invokeAll(rec1, rec2, rec3, rec4);

				juntarResultados(n, rec1.tablero, rec2.tablero, rec3.tablero, rec4.tablero);

			} else if (cuadrante == 2) {
				tablero[c[0]][c[1]] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				TrominoParalelo rec1 = new TrominoParalelo(c[0], c[1], n / 2, orig_x, orig_y);
				TrominoParalelo rec2 = new TrominoParalelo(c[0] + 1, c[1], n / 2, orig_x, orig_y + n / 2);
				TrominoParalelo rec3 = new TrominoParalelo(x, y, n / 2, orig_x + n / 2, orig_y);
				TrominoParalelo rec4 = new TrominoParalelo(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

				invokeAll(rec1, rec2, rec3, rec4);

				juntarResultados(n, rec1.tablero, rec2.tablero, rec3.tablero, rec4.tablero);

			} else if (cuadrante == 3) {
				tablero[c[0]][c[1]] = count;
				tablero[c[0]][c[1] + 1] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				TrominoParalelo rec1 = new TrominoParalelo(c[0], c[1], n / 2, orig_x, orig_y);
				TrominoParalelo rec2 = new TrominoParalelo(x, y, n / 2, orig_x, orig_y + n / 2);
				TrominoParalelo rec3 = new TrominoParalelo(c[0], c[1] + 1, n / 2, orig_x + n / 2, orig_y);
				TrominoParalelo rec4 = new TrominoParalelo(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

				invokeAll(rec1, rec2, rec3, rec4);

				juntarResultados(n, rec1.tablero, rec2.tablero, rec3.tablero, rec4.tablero);

			} else {
				tablero[c[0]][c[1]] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0]][c[1] + 1] = count;

				TrominoParalelo rec1 = new TrominoParalelo(c[0], c[1], n / 2, orig_x, orig_y);
				TrominoParalelo rec2 = new TrominoParalelo(c[0], c[1] + 1, n / 2, orig_x, orig_y + n / 2);
				TrominoParalelo rec3 = new TrominoParalelo(c[0] + 1, c[1], n / 2, orig_x + n / 2, orig_y);
				TrominoParalelo rec4 = new TrominoParalelo(x, y, n / 2, orig_x + n / 2, orig_y + n / 2);

				invokeAll(rec1, rec2, rec3, rec4);

				juntarResultados(n, rec1.tablero, rec2.tablero, rec3.tablero, rec4.tablero);

			}
		} else { // Caso básico
			if (tablero[orig_x][orig_y] != 0) {

				tablero[orig_x + 1][orig_y] = count;
				tablero[orig_x][orig_y + 1] = count;
				tablero[orig_x + 1][orig_y + 1] = count;

			} else if (tablero[orig_x + 1][orig_y] != 0) {

				tablero[orig_x][orig_y] = count;
				tablero[orig_x][orig_y + 1] = count;
				tablero[orig_x + 1][orig_y + 1] = count;

			} else if (tablero[orig_x][orig_y + 1] != 0) {

				tablero[orig_x][orig_y] = count;
				tablero[orig_x + 1][orig_y] = count;
				tablero[orig_x + 1][orig_y + 1] = count;
			} else {

				tablero[orig_x][orig_y] = count;
				tablero[orig_x + 1][orig_y] = count;
				tablero[orig_x][orig_y + 1] = count;
			}
		}
	}

	private int buscarCuadrante(int x, int y, int posX, int posY) {
		if (posX <= x && posY <= y) {
			return 1;
		} else if (posX <= x && posY > y) {
			return 2;
		} else if (posX > x && posY <= y) {
			return 3;
		}
		return 4;
	}

	private int[][] juntarResultados(int tamaño, int[][] r1, int[][] r2, int[][] r3, int[][] r4) {
		int[][] resultado = new int[tamaño][tamaño];

		for (int i = 0; i < tamaño / 2; i++) {
			for (int j = 0; j < tamaño / 2; j++) {
				resultado[i][j] = r1[i][j];
			}
		}

		for (int i = tamaño / 2; i < tamaño; i++) {
			for (int j = 0; j < tamaño / 2; j++) {
				resultado[i][j] = r2[i - tamaño / 2][j];
			}
		}

		for (int i = 0; i < tamaño / 2; i++) {
			for (int j = tamaño / 2; j < tamaño; j++) {
				resultado[i][j] = r3[i][j - tamaño / 2];
			}
		}

		for (int i = tamaño / 2; i < tamaño; i++) {
			for (int j = tamaño / 2; j < tamaño; j++) {
				resultado[i][j] = r4[i - tamaño / 2][j - tamaño / 2];
			}
		}

		return resultado;
	}

}
