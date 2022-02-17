package p11.matriz;

import p0.matriz.MatrizOperaciones;

public class MatrizOperacionesTiempos {

	private static MatrizOperaciones matriz;

	public static void main(String arg[]) {

		long t1 = 0, t2 = 0;
		int repeticiones = 500_000;
		@SuppressWarnings("unused")
		int result = 0;

		System.out.println("Repeticiones: " + repeticiones);

		for (int n = 3; n < 3100; n *= 2) {

			matriz = new MatrizOperaciones(n, -50, 50);

			t1 = System.currentTimeMillis();

			for (int rep = 0; rep < repeticiones; rep++) {
				result = matriz.sumarDiagonal2();
			}

			t2 = System.currentTimeMillis() - t1;

			System.out.println("n = " + n + "\tTiempo = " + t2 + "\t");
		}
	}
}
