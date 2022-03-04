package p31;

public class Sustraccion3 {

	static long cont;

	public static boolean rec3(int n) {
		if (n <= 0)
			cont++;
		else {
			cont++;
			rec3(n - 1);
			rec3(n - 1);
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]) {
		long t1, t2, cont;
//		int nVeces = Integer.parseInt(arg[0]);
		int nVeces = 10;
		boolean b = true;

		System.out.println("n\tt\trepeticiones");
		for (int n = 1; n <= 100; n++) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				cont = 0;
				b = rec3(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nVeces);
		}
	}

}