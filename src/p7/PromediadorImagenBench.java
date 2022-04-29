package p7;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;

public class PromediadorImagenBench {

	// Ajustes del banco de pruebas
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6/einstein_1_256.png";
//	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/src/p7/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/src/p7/out_bt";
	private static String OUT_DIR_BNB = Paths.get("").toAbsolutePath().toString() + "/src/p7/out_bnb";
	private static int N_IMGS = 6;
	private static double PORCENTAJE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Nivel de ruido - desvición estándar de una distrubución Gaussiana

	public static void main(String[] args) {

		int n_real, n_bad;
		PromediadorImagen img_avger;

		// Generación y testeo de un conjunto de imágenes
		n_bad = (int) ((PORCENTAJE_BAD / 100.) * N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

//		System.out.print("TESTING BACKTRACKING SIN BALANCEO:\n");
//		img_avger.splitSubsetsBacktracking();
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);
//
//		System.out.print("TESTING BRANCH AND BOUND:\n");
//		img_avger.branchAndBound();
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_BNB);

		// Medidas
		System.out.println("n\tt_bt\tt_bnb\tnodes_bt\tnodes_bnb\tzncc_bt\tzncc_bnb");
		for (int i = 2; i < 20; i++) {
			n_bad = (int) ((PORCENTAJE_BAD / 100.) * i);
			n_real = i - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

			long t1 = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking();
			double zncc_bt = img_avger.zncc();
			int counter_bt = img_avger.getCounter();
			long t_bt = System.currentTimeMillis() - t1;

			t1 = System.currentTimeMillis();
			img_avger.branchAndBound();
			double zncc_btnb = img_avger.zncc();
			int counter_bnb = img_avger.getCounter();
			long t_bnb = System.currentTimeMillis() - t1;

			System.out.println(i + "\t" + t_bt + "\t" + t_bnb + "\t" + counter_bt + "\t" + counter_bnb + "\t"
					+ round(zncc_bt, 4) + "\t" + round(zncc_btnb, 4));
		}
	}

	public static double round(double valor, int nDec) {
		BigDecimal bd = BigDecimal.valueOf(valor);
		bd = bd.setScale(nDec, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
