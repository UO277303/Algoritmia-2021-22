package p11.vector;

import java.util.Random;

public class Vector1 {

	static int[] v;

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]);

		v = new int[n];

		rellena(v);
		escribe(v);

		int s = suma(v);
		System.out.println("Suma de los elementos del vector = " + s);

		int[] m = new int[2];
		maximo(v, m);
		System.out.println("Valor del máximo del vector = " + m[1]);
		System.out.println("Posición del máximo del vector = " + m[0]);

	}

	public static void rellena(int[] a) {
		Random r = new Random();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			a[i] = r.nextInt(199) - 99;
		}
	}

	public static void escribe(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			System.out.println("Elemento " + i + " = " + a[i]);
		}

	}

	public static int suma(int[] a) {
		int s = 0;
		int n = a.length;
		for (int i = 0; i < n; i++)
			s = s + a[i];
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
