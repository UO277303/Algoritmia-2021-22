package p6_examen;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;

public class PromediadorImagenBench {

	// Ajustes del banco de pruebas
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6_examen/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/src/p6_examen/einstein_1_256.png";
//	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/src/p6_examen/out_g/";
//	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/src/p6_examen/out_bt";
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

//		System.out.print("TESTING EXAMEN VORAZ SIN BALANCEO:\n");
//		img_avger.vorazExamenSinBalanceo(N_IMGS);
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_G);
//
//		System.out.print("TESTING EXAMEN VORAZ CON BALANCEO:\n");
//		img_avger.vorazExamenConBalanceo(N_IMGS, 1);
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_G);
//
//		System.out.print("TESTING EXAMEN BACKTRACKING SIN BALANCEO:\n");
//		img_avger.backtrackingExamenSinBalanceo();
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);
//
//		System.out.print("TESTING EXAMEN BACKTRACKING CON BALANCEO:\n");
//		img_avger.backtrackingExamenConBalanceo(1);
//		System.out.printf("  -ZNCC: %f\n", img_avger.zncc());
//		System.out.printf("  -Contador: %d\n", img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);

		// Medidas
		System.out.println("n\tt_bt_p6\tt_bt_ex");
		for (int i = 2; i < 20; i++) {
			n_bad = (int) ((PORCENTAJE_BAD / 100.) * i);
			n_real = i - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

			img_avger.splitSubsetsGreedy(i);

			long t1 = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking();
			long t_bt_p6 = System.currentTimeMillis() - t1;

			t1 = System.currentTimeMillis();
			img_avger.backtrackingExamenSinBalanceo();
			long t_bt_ex = System.currentTimeMillis() - t1;

			System.out.println(i + "\t" + t_bt_p6 + "\t" + t_bt_ex);
		}
	}

	public static double round(double valor, int nDec) {
		BigDecimal bd = BigDecimal.valueOf(valor);
		bd = bd.setScale(nDec, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
