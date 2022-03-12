package p3.tromino;

public class TrominoTiempos {

	public static void main(String[] args) {
		int rep = 1;

		Tromino tromino = new Tromino();

		System.out.println("n\tt\trep");
		for (int n = 2; n < 1_000_000; n *= 2) {
			long t1 = System.currentTimeMillis();

			for (int i = 0; i < rep; i++) {
				tromino.crearTablero(n, 0, 0);
			}

			long t2 = System.currentTimeMillis() - t1;

			System.out.println(n + "\t" + t2 + "\t" + rep);
		}
	}

}
