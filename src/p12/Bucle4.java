package p12;

public class Bucle4 {

	public static long bucle4(int n) {
		long cont = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					for (int l = 1; l <= n; l++) {
						cont++;
					}
				}
			}
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
//		int nVeces = Integer.parseInt(arg[0]);
		int nVeces = 1;

		System.out.println("n\ttiempo\tcontador");
		for (int n = 512; n <= 100_000; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle4(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + c);

		}

	}
}
