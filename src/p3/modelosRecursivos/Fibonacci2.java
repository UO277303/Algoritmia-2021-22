package p3.modelosRecursivos;

/* This program serves to measure times automatically increasing the size of 
 * the problem (n) and also using a time scale determined by nTimes, 
 * which is taken from the argument arg[1] 
 * It also gets as an execution argument (arg[0]) the Fibonacci method 
 * on which we will focus the measurement (opcions 1,2,3,4 respectively) 
 */
public class Fibonacci2 {

	public static void main(String[] arg) {
//		int option = Integer.parseInt(arg[0]);
//		int nTimes = Integer.parseInt(arg[1]);
		int option = 1;
		int nTimes = 2_500_000;

		long t1, t2;
		@SuppressWarnings("unused")
		int solution = 0;

		System.out.println("Opci�n: " + option);
		System.out.println("n\tt\trepeticiones");
		for (int n = 10; n < 60; n++) { // from n = 10 to n = 60
			if (option == 1) { // fib1
				t1 = System.currentTimeMillis();

				for (int repeticiones = 1; repeticiones <= nTimes; repeticiones++)
					solution = Fibonacci1.fib1(n);

				t2 = System.currentTimeMillis();
				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else if (option == 2) { // fib2
				int[] v = new int[60]; // for the second method
				t1 = System.currentTimeMillis();

				for (int repeticiones = 1; repeticiones <= nTimes; repeticiones++)
					solution = Fibonacci1.fib2(n, v);

				t2 = System.currentTimeMillis();
				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else if (option == 3) { // fib3
				t1 = System.currentTimeMillis();

				for (int repeticiones = 1; repeticiones <= nTimes; repeticiones++)
					solution = Fibonacci1.fib3(n);

				t2 = System.currentTimeMillis();
				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else if (option == 4) { // fib4
				t1 = System.currentTimeMillis();

				for (int repeticiones = 1; repeticiones <= nTimes; repeticiones++)
					solution = Fibonacci1.fib4(n);

				t2 = System.currentTimeMillis();
				System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes);
			} else
				System.out.println("INCORRECT OPTION");

		}
	}

}
