package p3.modelosRecursivos;

public class Division4 {

	static long cont;

	public static boolean rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cont++;
				}
			}
			rec4(n / 3);
			rec4(n / 3);
			rec4(n / 3);
			rec4(n / 3);
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]) {
		long t1, t2, cont;
//		int nVeces = Integer.parseInt(arg[0]);
		int nVeces = 1_000_000;
		boolean b = true;

		System.out.println("n\tt\trepeticiones");
		for (int n = 1; n <= 10_000_000; n++) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				cont = 0;
				b = rec4(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nVeces);

		}
	}

}