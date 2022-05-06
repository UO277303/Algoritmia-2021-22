package p3.tromino;

public class Tromino {

	int[][] tablero;
	int count = 0;

	/**
	 * Crea el tablero y empieza a resolver el trominó
	 * 
	 * @param n      Tamaño del tablero
	 * @param xVacio Posición x de la casilla vacía
	 * @param yVacio Posición y de la casilla vacía
	 */
	public void crearTablero(int n, int xVacio, int yVacio) {
		if (n > 0 && potenciaDe2(n) && xVacio < n && yVacio < n) {
			tablero = new int[n][n];
			tablero[xVacio][yVacio] = -1;
			tromino(xVacio, yVacio, n, 0, 0);
		} else {
			throw new IllegalArgumentException("Error en los parámetros.");
		}
	}

	/**
	 * Comprueba si el número pasado por parámetro es potencia de dos
	 * 
	 * @param x Número a comprobar
	 * @return true si x es potencia de 2
	 */
	private boolean potenciaDe2(int x) {
		return Math.log(x) / Math.log(2) % 1 == 0;
	}

	/**
	 * Método recursivo para resolver el trominó - Si no es el caso base,
	 * dependiendo del cuadrante coloca la pieza en el lugar correspondiente, y hace
	 * 4 llamadas recursivas (una por cada cuadrante) - Si es el caso base, coloca
	 * la pieza en el resto de casillas
	 * 
	 * @param x      Posición x de la casilla ya ocupada
	 * @param y      Posición y de la casilla ya ocupada
	 * @param n      Tamaño del subtablero
	 * @param orig_x Posición x del tablero donde empieza el subtablero
	 * @param orig_y Posición y del tablero donde empieza el subtablero
	 */
	public void tromino(int x, int y, int n, int orig_x, int orig_y) {
		int cuadrante = buscarCuadrante(orig_x + n / 2 - 1, orig_y + n / 2 - 1, x, y);

		// Centro del subtablero
		int[] c = { orig_x + n / 2 - 1, orig_y + n / 2 - 1 };

		count++;

		if (n > 2) {
			if (cuadrante == 1) {
				tablero[c[0]][c[1] + 1] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				tromino(x, y, n / 2, orig_x, orig_y);
				tromino(c[0], c[1] + 1, n / 2, orig_x, orig_y + n / 2);
				tromino(c[0] + 1, c[1], n / 2, orig_x + n / 2, orig_y);
				tromino(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

			} else if (cuadrante == 2) {
				tablero[c[0]][c[1]] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				tromino(c[0], c[1], n / 2, orig_x, orig_y);
				tromino(c[0] + 1, c[1], n / 2, orig_x, orig_y + n / 2);
				tromino(x, y, n / 2, orig_x + n / 2, orig_y);
				tromino(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

			} else if (cuadrante == 3) {
				tablero[c[0]][c[1]] = count;
				tablero[c[0]][c[1] + 1] = count;
				tablero[c[0] + 1][c[1] + 1] = count;

				tromino(c[0], c[1], n / 2, orig_x, orig_y);
				tromino(x, y, n / 2, orig_x, orig_y + n / 2);
				tromino(c[0], c[1] + 1, n / 2, orig_x + n / 2, orig_y);
				tromino(c[0] + 1, c[1] + 1, n / 2, orig_x + n / 2, orig_y + n / 2);

			} else {
				tablero[c[0]][c[1]] = count;
				tablero[c[0] + 1][c[1]] = count;
				tablero[c[0]][c[1] + 1] = count;

				tromino(c[0], c[1], n / 2, orig_x, orig_y);
				tromino(c[0], c[1] + 1, n / 2, orig_x, orig_y + n / 2);
				tromino(c[0] + 1, c[1], n / 2, orig_x + n / 2, orig_y);
				tromino(x, y, n / 2, orig_x + n / 2, orig_y + n / 2);
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

	/**
	 * Devuelve el cuadrante en el que se encuentra la casilla cuyas posiciones se
	 * pasan por parámetro
	 * 
	 * @param x    Posición x del centro del tablero
	 * @param y    Posición y del centro del tablero
	 * @param posX Posición x del punto a comprobar
	 * @param posY Posición y del punto a comprobar
	 * @return El número de cuadrante correspondiente
	 */
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

	public void imprimirTablero() {
		System.out.println("--- TROMINÓ ---");
		for (int i = 0; i < tablero[0].length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				if (j != 0) {
					System.out.print("\t");
				}
				System.out.print("|\t" + tablero[i][j]);
				if (j == tablero[0].length - 1) {
					System.out.print("\t|\n");
				}
			}
		}
		System.out.println();
	}

}
