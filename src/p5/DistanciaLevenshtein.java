package p5;

public class DistanciaLevenshtein {

	private char[] cad1;
	private char[] cad2;

	private int[][] matriz;

	public DistanciaLevenshtein(String cad1, String cad2) {
		this.cad1 = cad1.toCharArray();
		this.cad2 = cad2.toCharArray();
		matriz = new int[cad1.length() + 1][cad2.length() + 1];
		inicializarMatriz();
	}

	public int calcularDistancia() {
		for (int i = 1; i < matriz.length; i++) {
			for (int j = 1; j < matriz[0].length; j++) {
				if (cad1[i - 1] == cad2[j - 1]) {
					matriz[i][j] = matriz[i - 1][j - 1];
				} else {
					matriz[i][j] = 1 + min(matriz[i - 1][j - 1], matriz[i - 1][j], matriz[i][j - 1]);
				}
			}
		}
		return matriz[cad1.length][cad2.length];
	}

	private int min(int a, int b, int c) {
		int min = a;
		if (b < min) {
			min = b;
		}
		if (c < min) {
			min = c;
		}
		return min;
	}

	public void imprimirMatriz() {
		System.out.println("Cadena 1: " + getCadena(cad1));
		System.out.println("Cadena 2: " + getCadena(cad2));
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print("\t" + matriz[i][j]);
			}
			System.out.println();
		}
		System.out.println("Distancia de Levenshtein = " + matriz[cad1.length][cad2.length]);
	}

	private void inicializarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = i;
		}
		for (int i = 0; i < matriz[0].length; i++) {
			matriz[0][i] = i;
		}
	}

	private String getCadena(char[] cad) {
		StringBuilder sb = new StringBuilder();
		for (char c : cad) {
			sb.append(c);
		}
		return sb.toString();
	}

}
