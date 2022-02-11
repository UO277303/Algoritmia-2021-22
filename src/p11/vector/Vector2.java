package p11.vector;

import java.util.Random;

public class Vector2 {

	static int[] v;

	public static void main(String arg[]) {

		int n = Integer.parseInt(arg[0]);

		v = new int[n];

		rellena(v);

		long t1 = System.currentTimeMillis();

		int s = suma(v);

		long t2 = System.currentTimeMillis() - t1;

		System.out.println("Suma de los elementos del vector = " + s);
		System.out.println("Tiempo = " + t2);
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
