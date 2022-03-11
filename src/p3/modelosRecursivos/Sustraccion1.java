package p3.modelosRecursivos;

public class Sustraccion1 {

	static long cont;

	public static boolean rec1(int n) {
		if (n <= 0)
			cont++;
		else {
			cont++;
			rec1(n - 1);
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]) {
		long t1, t2, cont;
//		int nVeces = Integer.parseInt(arg[0]);
		int nVeces = 100_000;
		boolean b = true;

		System.out.println("n\tt\trepeticiones");
		for (int n = 1; n <= 100_000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				cont = 0;
				b = rec1(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nVeces);
		}
	}

}