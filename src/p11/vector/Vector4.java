package p11.vector;

import java.util.Random;

public class Vector4 {

	static int[] v;

	public static void main(String arg[]) {

		long t1 = 0, t2 = 0;
		int repeticiones = 1_000;
		@SuppressWarnings("unused")
		int result = 0;

		System.out.println("Repeticiones: " + repeticiones);

		for (int n = 5_000_000; n < 2_000_000_000; n *= 3) {

			v = new int[n];
			rellena(v);

			t1 = System.currentTimeMillis();

			for (int rep = 0; rep < repeticiones; rep++) {
				result = suma(v);
//				maximo(v, new int[2]);
			}

			t2 = System.currentTimeMillis() - t1;

			System.out.println("n = " + n + "\tTiempo = " + t2 + "\t");
		}
	}

	public static void rellena(int[] a) {
		Random r = new Random();
		int n = a.length;

		for (int i = 0; i < n; i++) {
			a[i] = r.nextInt(199) - 99;
		}
	}

	public static int suma(int[] a) {
		int s = 0;
		int n = a.length;

		for (int i = 0; i < n; i++) {
			s = s + a[i];
		}

		return s;
	}

	public static void maximo(int[] a, int[] m) {
		int n = a.length;

		m[0] = 0;
		m[1] = a[0];

		for (int i = 1; i < n; i++) {
			if (a[i] > m[1]) {
				m[0] = i;
				m[1] = a[i];
			}
		}
	}
}
