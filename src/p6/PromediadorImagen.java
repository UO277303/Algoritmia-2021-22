package p6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromediadorImagen {

	private Imagen real_img, bad_img; // para almacenar las imagenes con patron bueno y malo (negativo del malo)
	private Imagen avg_img, half1_img, half2_img; // para almacenar los promedios del subconjunto 1 y 2
	private Imagen[] dataset; // almacena el conjunto de de imagenes generadas (buenas y malas)
	private int[] sol; // array que determina donde poner las imágenes 0->no asignada, 1->primer
						// subconjunto, 2->segundo subconjunto
	private int[] bestSol; // mejor solución
	private int width, height; // ancho y alto de las imágenes
	// backtracking variables
	private int counter; // contador de nodos en el arbol implícito
	private double max_zncc; // donde almacenar el ZNCC final

	/**
	 * Constructor
	 * 
	 * @real_path ruta del modelo de imagen "buena" (patrón a encontrar) en disco
	 * @bad_path ruta del modelo de imagen "mala"
	 * @n_real numero de imagenes buenas (>= 1)
	 * @n_bad numero de imagenes "malas" (tiene que ser menor que las buenas)
	 * @s_noise standard deviation for noise
	 */
	public PromediadorImagen(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {

		assert (n_real >= 1) && (n_bad < n_real);

		// Cargando los patrones de referencia (buena y mala)
		this.real_img = new Imagen(real_path);
		this.bad_img = new Imagen(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();

		// Se crean con conjunto de imagenes con un array ordenado aleatoriamente para
		// posicionar
		// las imagenes buenas y malas aleatoriamente
		int total_imgs = n_real + n_bad; // numero total de imágenes
		this.dataset = new Imagen[total_imgs];
		this.sol = new int[total_imgs]; // dónde se almacena la solución actual (combinación de asignaciones): 0->no
										// asignada, 1->primer subconjunto, 2->segundo subconjunto
		this.bestSol = new int[total_imgs]; // dónde se almacena la mejor solución
		int[] rand_index = this.randomIndexes(total_imgs); // array con las posiciones aleatorias
		Imagen hold_img; // imagen temporal
		int region = 0; // 0-arriba, 1-bajo, 2-izquierda, 3-derecha
		for (int i = 0; i < n_real; i++) { // imágenes buenas
			hold_img = new Imagen(this.width, this.height); // creación de la imagen
			hold_img.addSignal(this.real_img); // añadir los valores de los píxeles
			hold_img.suppressRegion(region); // suprimir una region
			hold_img.addNoise(s_noise); // añadir ruido
			this.dataset[rand_index[i]] = hold_img; // incluir la imagne en una posción aleatorio de dataset
			if (region == 3)
				region = 0;
			else
				region++;
		}
		region = 0;
		for (int i = n_real; i < n_real + n_bad; i++) { // bucle para las imágenes malas
			hold_img = new Imagen(this.width, this.height);
			hold_img.addSignal(this.bad_img);
			hold_img.invertSignal();
			hold_img.suppressRegion(region);
			hold_img.addNoise(s_noise);
			this.dataset[rand_index[i]] = hold_img;
			if (region == 3)
				region = 0;
			else
				region++;
		}
	}

	/**
	 * Gener una array con valores de posiciones aleatorios
	 * 
	 * @param n longitud del array
	 * @return
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}

	/**
	 * Almacena las imágenes generadas según la mejor solución encontrada
	 * 
	 * @out_dir directorio donde se almacenan las imágenes
	 */
	public void saveResults(String out_dir) {
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for (int i = 0; i < this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}

	/**
	 * @return devuelve el número de pasos requeridos por el algoritmo
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Calcula el ZNCC entre los promedios de los dos subconjuntos de imágenes
	 * 
	 * @return el valor de ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}

	/**
	 * Greedy algorithm: random instances for each half, the best one is the final
	 * solution
	 * 
	 * @n_tries numero de intentos aleatorios
	 */
	public void splitSubsetsGreedy(int n_tries) {
		int count = n_tries;
		double zncc = -1;

		this.half1_img = new Imagen(width, height);
		this.half2_img = new Imagen(width, height);
		this.avg_img = new Imagen(width, height);

		while (count > 0) {

			int[] v = new int[dataset.length];
			for (int i = 0; i < v.length; i++) {
				v[i] = (int) (Math.random() * 3);
			}

			this.sol = v;

			for (int i = 0; i < v.length; i++) {
				if (v[i] == 1) {
					this.half1_img.addSignal(dataset[i]);
				} else if (v[i] == 2) {
					this.half2_img.addSignal(dataset[i]);
				}
			}

			double new_zncc = zncc();

			if (new_zncc > zncc) {
				zncc = new_zncc;
				this.bestSol = sol;
			}

			this.half1_img.addSignal(this.half2_img);
			Imagen aux = half1_img;
			aux.addSignal(half2_img);
			this.avg_img = aux;

			count--;
			this.counter++;
		}

	}

	/**
	 * Algoritmo backtracking con condición balanceo
	 * 
	 * @max_unbalancing: (condición de poda) determina la diferencia máxima en el
	 *                   número de imágenes entre los dos subconjuntos
	 */
	/*
	 * El array sol debe tener el mismo numero de 0 que de 1 (+- max_unbalancing) Si
	 * len=6 y hay tres 1 metidos, no permitir meter 1 (solo 2) Crear metodos para
	 * contar numero de unos o doses
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		// TODO
	}

	/**
	 * Algoritmo backtracking sin condición de balanceo.
	 */
	public void splitSubsetsBacktracking() {

		this.half1_img = new Imagen(width, height);
		this.half2_img = new Imagen(width, height);
		this.avg_img = new Imagen(width, height);

		backtrackingRec(0);
	}

	/*
	 * Primero comprobar si estamos en el caso base (nivel 0) [ Aquí es donde se van
	 * a hacer cosas (una vez se tenga una solución) Más o menos como el greedy,
	 * calcular imagenes, zncc, comparar ] En otro caso, 3 llamadas recursivas (de
	 * nivel+1) Diferencia entre llamadas: si es caso 0, 1, 2 (imagen) -> pasarlo
	 * por parámetro o, antes de primera llamada llamada, poner sol[nivel] a 0,
	 * antes de segunda sol[nivel] = 1, igual con 2
	 */
	private void backtrackingRec(int level) {
		if (level == sol.length - 1) {
			for (int i = 0; i < sol.length; i++) {
				if (sol[i] == 1) {
					this.half1_img.addSignal(dataset[i]);
				} else if (sol[i] == 2) {
					this.half2_img.addSignal(dataset[i]);
				}
			}

			double new_zncc = zncc();

			if (new_zncc > max_zncc) {
				max_zncc = new_zncc;
				this.bestSol = sol;
			}

			this.half1_img.addSignal(this.half2_img);
			Imagen aux = half1_img;
			aux.addSignal(half2_img);
			this.avg_img = aux;

			this.counter++;

		} else {
			sol[level] = 0;
			backtrackingRec(level + 1);
			sol[level] = 1;
			backtrackingRec(level + 1);
			sol[level] = 2;
			backtrackingRec(level + 1);
		}

	}

}
