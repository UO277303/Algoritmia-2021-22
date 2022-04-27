package p3.tromino;

import java.util.concurrent.ForkJoinPool;

public class TrominoTiempos {

	public static void main(String[] args) {
		int rep = 10000;

		Tromino tromino = new Tromino();

		System.out.println("Algoritmo secuencial:");
		System.out.println("n\tt\trep");
		for (int n = 2; n < 100; n *= 2) {
			long t1 = System.currentTimeMillis();

			for (int i = 0; i < rep; i++) {
				tromino.crearTablero(n, 0, 0);
			}

			long t2 = System.currentTimeMillis() - t1;

			System.out.println(n + "\t" + t2 + "\t" + rep);
		}

		rep = 1000000;

		System.out.println("\nAlgoritmo paralelo:");
		System.out.println("n\tt\trep");
		for (int n = 2; n < 1_000; n *= 2) {
			TrominoParalelo tromParalelo = new TrominoParalelo(0, 0, n, 0, 0);
			ForkJoinPool pool = new ForkJoinPool();

			long t1 = System.currentTimeMillis();

			for (int i = 0; i < rep; i++) {
				pool.invoke(tromParalelo);
			}

			long t2 = System.currentTimeMillis() - t1;

			System.out.println(n + "\t" + t2 + "\t" + rep);
		}
	}

}
