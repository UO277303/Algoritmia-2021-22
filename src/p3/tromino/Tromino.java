package p3.tromino;

public class Tromino {

	int[][] tablero;
	int count = 0;

	/**
	 * Crea el tablero y empieza a resolver el tromin�
	 * 
	 * @param n      Tama�o del tablero
	 * @param xVacio Posici�n x de la casilla vac�a
	 * @param yVacio Posici�n y de la casilla vac�a
	 */
	public void crearTablero(int n, int xVacio, int yVacio) {
		if (n > 0 && potenciaDe2(n) && xVacio < n && yVacio < n) {
			tablero = new int[n][n];
			tablero[xVacio][yVacio] = -1;
			tromino(xVacio, yVacio, n, 0, 0);
		} else {
			throw new IllegalArgumentException("Error en los par�metros.");
		}
	}

	/**
	 * Comprueba si el n�mero pasado por par�metro es potencia de dos
	 * 
	 * @param x N�mero a comprobar
	 * @return true si x es potencia de 2
	 */
	private boolean potenciaDe2(int x) {
		return Math.log(x) / Math.log(2) % 1 == 0;
	}

	/**
	 * M�todo recursivo para resolver el tromin� - Si no es el caso base,
	 * dependiendo del cuadrante coloca la pieza en el lugar correspondiente, y hace
	 * 4 llamadas recursivas (una por cada cuadrante) - Si es el caso base, coloca
	 * la pieza en el resto de casillas
	 * 
	 * @param x      Posici�n x de la casilla ya ocupada
	 * @param y      Posici�n y de la casilla ya ocupada
	 * @param n      Tama�o del subtablero
	 * @param orig_x Posici�n x del tablero donde empieza el subtablero
	 * @param orig_y Posici�n y del tablero donde empieza el subtablero
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
		} else { // Caso b�sico
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
	 * pasan por par�metro
	 * 
	 * @param x    Posici�n x del centro del tablero
	 * @param y    Posici�n y del centro del tablero
	 * @param posX Posici�n x del punto a comprobar
	 * @param posY Posici�n y del punto a comprobar
	 * @return El n�mero de cuadrante correspondiente
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
		System.out.println("--- TROMIN� ---");
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
