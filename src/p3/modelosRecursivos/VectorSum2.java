package p3.modelosRecursivos;

/* This program serves to measure times automatically increasing the size of 
 * the problem (n) and also using a time scale determined by nTimes, 
 * which is taken from the argument arg[1] 
 * It also gets as an execution argument (arg[0]) the sum method on which
 * we will focus the measurement (options 1,2,3 respectively) 
 */
public class VectorSum2 {
	static int[] v;

	public static void main(String arg[]) {
//		int nTimes = Integer.parseInt(arg[1]);
//		int option = Integer.parseInt(arg[0]);
		int nTimes = 2_500_000;
		int option = 1;

		@SuppressWarnings("unused")
		int sum = 0;
		long t1, t2;

		System.out.println("option = " + option);
		System.out.println("n\tt\trepeticiones");
		for (int n = 3; n <= 100000; n *= 2) { // n is incremented by *2
			v = new int[n];
			VectorSum1.fillIn(v);

			if (option == 1) { // sum1
				t1 = System.currentTimeMillis();

				for (int repetition = 1; repetition <= nTimes; repetition++)
					sum = VectorSum1.sum1(v);
				t2 = System.currentTimeMillis();

				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else if (option == 2) { // sum2
				t1 = System.currentTimeMillis();

				for (int repetition = 1; repetition <= nTimes; repetition++)
					sum = VectorSum1.sum2(v);

				t2 = System.currentTimeMillis();

				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else if (option == 3) { // sum3
				t1 = System.currentTimeMillis();

				for (int repetition = 1; repetition <= nTimes; repetition++)
					sum = VectorSum1.sum3(v);

				t2 = System.currentTimeMillis();

				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else {
				System.out.println("INCORRECT OPTION");
			}
		}
	}

}
