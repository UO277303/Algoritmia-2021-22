package p6;

import java.nio.file.Paths;

public class PromediadorImagenBench {

	// Ajustes del banco de pruebas
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6/einstein_1_256.png";
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/src/p6/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/src/p6/out_bt";
	private static int N_IMGS = 8;
	private static double PORCENTAJE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Nivel de ruido - desvici�n est�ndar de una distrubuci�n Gaussiana

	public static void main(String[] args) {

		int n_real, n_bad;
		PromediadorImagen img_avger;

		// Generaci�n y testeo de un conjunto de im�genes
		n_bad = (int) ((PORCENTAJE_BAD / 100.) * N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

		System.out.print("TESTING VORAZ:\n");
		img_avger.splitSubsetsGreedy(N_IMGS);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);

		System.out.print("TESTING BACKTRACKING CON BALANCEO:\n");
		img_avger.splitSubsetsBacktracking(1);
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		System.out.print("TESTING BACKTRACKING SIN BALANCEO:\n");
		img_avger.splitSubsetsBacktracking();
		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		// Medidas
		System.out.println("n\tt_bt\tt_bt_b\tzncc_voraz\tzncc_bt\tzncc_bt_b");
		for (int i = 2; i < 20; i++) {
			n_bad = (int) ((PORCENTAJE_BAD / 100.) * i);
			n_real = i - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

			img_avger.splitSubsetsGreedy(i);
			double zncc_voraz = img_avger.zncc();

			long t1 = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking();
			double zncc_bt = img_avger.zncc();
			long t_bt = System.currentTimeMillis() - t1;

			t1 = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking(1);
			double zncc_bt_bal = img_avger.zncc();
			long t_bt_bal = System.currentTimeMillis() - t1;

			System.out.println(
					i + "\t" + t_bt + "\t" + t_bt_bal + "\t" + zncc_voraz + "\t" + zncc_bt + "\t" + zncc_bt_bal);
		}
	}

}
