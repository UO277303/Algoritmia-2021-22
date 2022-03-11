package p3.tromino;

public class Tromino {

	static int[][] tablero;
	int count = 0;

	/**
	 * Método que crea el tablero inicial y ejecuta el método
	 * 
	 * @param n      Tamaño del tablero (nxn)
	 * @param xVacio Posición x de la casilla vacía
	 * @param yVacio Posición y de la casilla vacía
	 * @throws IllegalArgumentException Si algún parámetro es inválido
	 */
	public void crearTablero(int n, int xVacio, int yVacio) {
		if (n > 0 && potenciaDe2(n) && xVacio < n && yVacio < n) {
			tablero = new int[n][n];
			tablero[xVacio][yVacio] = -1;
			tromino(tablero, n, xVacio, yVacio, 0, 0);
		} else {
			throw new IllegalArgumentException("Error en los parámetros.");
		}
	}

	private boolean potenciaDe2(int x) {
		return Math.log(x) / Math.log(2) % 1 == 0;
	}

	/**
	 * Método recursivo que completa los subtableros añadiendo las fichas en los
	 * lugares correspondientes
	 * 
	 * @param st  Tablero
	 * @param n   Tamaño del subtablero
	 * @param xOc Posición x de la casilla ocupada
	 * @param yOc Posición y de la casilla ocupada
	 * @param xEm Posición x del tablero anterior en la que empieza el subtablero
	 * @param yEm Posición y del tablero anterior en la que empieza el subtablero
	 */
	public void tromino(int[][] st, int n, int xOc, int yOc, int xEm, int yEm) {
		// Guardo el centro del tablero (esquina inferior derecha del primer cuadrante)
		int[] centro = { xEm + n / 2 - 1, yEm + n / 2 - 1 };

		int cuadrante = buscarCuadrante(centro[0], centro[1], xOc, yOc);

		if (cuadrante == 1) { // Primer cuadrante
			count++;
			st[centro[0]][centro[1] + 1] = count;
			st[centro[0] + 1][centro[1]] = count;
			st[centro[0] + 1][centro[1] + 1] = count;
			imprimirTablero();
		} else if (cuadrante == 2) { // Segundo cuadrante
			count++;
			st[centro[0]][centro[1]] = count;
			st[centro[0] + 1][centro[1]] = count;
			st[centro[0] + 1][centro[1] + 1] = count;
			imprimirTablero();
		} else if (cuadrante == 3) { // Tercer cuadrante
			count++;
			st[centro[0]][centro[1]] = count;
			st[centro[0]][centro[1] + 1] = count;
			st[centro[0] + 1][centro[1] + 1] = count;
			imprimirTablero();
		} else { // Cuarto cuadrante
			count++;
			st[centro[0]][centro[1]] = count;
			st[centro[0] + 1][centro[1]] = count;
			st[centro[0]][centro[1] + 1] = count;
			imprimirTablero();
		}

		if (n > 2) {
			if (cuadrante == 1) {
				tromino(st, n / 2, centro[0], centro[1], xOc, yOc);
				tromino(st, n / 2, centro[0] + 1, centro[1], xEm + n / 2, yEm);
				tromino(st, n / 2, centro[0], centro[1] + 1, xEm, yEm + n / 2);
				tromino(st, n / 2, centro[0] + 1, centro[1] + 1, xEm + n / 2, yEm + n / 2);
			} else if (cuadrante == 2) {
				tromino(st, n / 2, centro[0], centro[1], xEm, yEm);
				tromino(st, n / 2, centro[0] + 1, centro[1], xOc, yOc);
				tromino(st, n / 2, centro[0], centro[1] + 1, xEm, yEm + n / 2);
				tromino(st, n / 2, centro[0] + 1, centro[1] + 1, xEm + n / 2, yEm + n / 2);
			} else if (cuadrante == 3) {
				tromino(st, n / 2, centro[0], centro[1], xEm, yEm);
				tromino(st, n / 2, centro[0] + 1, centro[1], xEm + n / 2, yEm);
				tromino(st, n / 2, centro[0], centro[1] + 1, xOc, yOc);
				tromino(st, n / 2, centro[0] + 1, centro[1] + 1, xEm + n / 2, yEm + n / 2);
			} else {
				tromino(st, n / 2, centro[0], centro[1], xEm, yEm);
				tromino(st, n / 2, centro[0] + 1, centro[1], xEm + n / 2, yEm);
				tromino(st, n / 2, centro[0], centro[1] + 1, xEm, yEm + n / 2);
				tromino(st, n / 2, centro[0] + 1, centro[1] + 1, xOc, yOc);
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

	public void imprimirTablero() {
		System.out.println("--- TROMINÓ ---");
		for (int i = 0; i < tablero[0].length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				System.out.print("\t|\t" + tablero[i][j]);
				if (j == tablero[0].length - 1) {
					System.out.print("\t|\n");
				}
			}
		}
		System.out.println();
	}

	///////////////////////

	public void tromino_2(int[][] st, int n, int xOc, int yOc, int xEm, int yEm) {
		// Guardo el centro del tablero (esquina inferior derecha del primer cuadrante)
		int[] centro = { xEm + n / 2 - 1, yEm + n / 2 - 1 };

		if (n <= 2) {
			count++;
			int aux = st[xOc][yOc];
			st[centro[0]][centro[1]] = count;
			st[centro[0] + 1][centro[1]] = count;
			st[centro[0]][centro[1] + 1] = count;
			st[centro[0] + 1][centro[1] + 1] = count;
			st[xOc][yOc] = aux;
		} else {
			// Divido el tablero en 4 cuadrantes, guardando para cada uno la posición de
			// arriba a la izquierda
			int[] C1 = { xEm, yEm };
			int[] C2 = { xEm, yEm + n / 2 };
			int[] C3 = { xEm + n / 2, yEm };
			int[] C4 = { xEm + n / 2, yEm + n / 2 };

			// Casilla que se marca según el cuadrante
			int[] mC1 = { centro[0], centro[1] };
			int[] mC2 = { centro[0], centro[1] + 1 };
			int[] mC3 = { centro[0] + 1, centro[1] };
			int[] mC4 = { centro[0] + 1, centro[1] + 1 };

			if (xOc <= centro[0] && yOc <= centro[1]) { // Primer cuadrante
				count++;
				st[centro[0]][centro[1] + 1] = count;
				st[centro[0] + 1][centro[1]] = count;
				st[centro[0] + 1][centro[1] + 1] = count;
				mC1[0] = xOc;
				mC1[1] = yOc;
			} else if (xOc <= centro[0] && yOc > centro[1]) { // Segundo cuadrante
				count++;
				st[centro[0]][centro[1]] = count;
				st[centro[0] + 1][centro[1]] = count;
				st[centro[0] + 1][centro[1] + 1] = count;
				mC2[0] = xOc;
				mC2[1] = yOc;
			} else if (xOc > centro[0] && yOc <= centro[1]) { // Tercer cuadrante
				count++;
				st[centro[0]][centro[1]] = count;
				st[centro[0]][centro[1] + 1] = count;
				st[centro[0] + 1][centro[1] + 1] = count;
				mC3[0] = xOc;
				mC3[1] = yOc;
			} else { // Cuarto cuadrante
				count++;
				st[centro[0]][centro[1]] = count;
				st[centro[0]][centro[1] + 1] = count;
				st[centro[0] + 1][centro[1]] = count;
				mC4[0] = xOc;
				mC4[1] = yOc;
			}

			imprimirTablero();

			tromino(st, n / 2, mC1[0], mC1[1], C1[0], C1[1]);
			tromino(st, n / 2, mC2[0], mC2[1], C2[0], C2[1]);
			tromino(st, n / 2, mC3[0], mC3[1], C3[0], C3[1]);
			tromino(st, n / 2, mC4[0], mC4[1], C4[0], C4[1]);
		}
	}

	public void crearTablero_2(int n, int xVacio, int yVacio) {
		if (n > 0 && potenciaDe2(n) && xVacio < n && yVacio < n) {
			tablero = new int[n][n];
			tablero[xVacio][yVacio] = -1;
			tromino_2(tablero, n, xVacio, yVacio, 0, 0);
		} else {
			throw new IllegalArgumentException("Error en los parámetros.");
		}
	}

}
