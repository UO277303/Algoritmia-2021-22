package p5;

public class DistanciaLevenshteinTiempos {

	public static void main(String[] args) {
//		DistanciaLevenshtein d = new DistanciaLevenshtein("ABRACADABRA", "BARCAZAS");
//		DistanciaLevenshtein d = new DistanciaLevenshtein("ELEFANTE", "RELEVANTE");
//		DistanciaLevenshtein d = new DistanciaLevenshtein("ABRACADABRA", "RELEVANTE");
//		d.calcularDistancia();
//		d.imprimirMatriz();

		long t1, t2;
		int rep = 10;

		System.out.println("n\tt\trep\tresult");
		for (int n = 1; n < 1_000_000; n *= 2) {
			String cad1 = cadenaAleatoria(n);
			String cad2 = cadenaAleatoria(n);
			DistanciaLevenshtein d = new DistanciaLevenshtein(cad1, cad2);
			int result = 0;

			t1 = System.currentTimeMillis();

			for (int r = 0; r < rep; r++) {
				result = d.calcularDistancia();
			}

			t2 = System.currentTimeMillis() - t1;

			System.out.println(n + "\t" + t2 + "\t" + rep + "\t" + result);
		}
	}

	static String cadenaAleatoria(int tamaño) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tamaño; i++) {
			sb.append(chars.charAt((int) (Math.random() * chars.length())));
		}
		return sb.toString();
	}

}
