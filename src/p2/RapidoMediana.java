package p2;

/**
 * Este programa sirve para ordenar n elementos con el algoritmo mejor. Es el
 * QUICKSORT
 */
public class RapidoMediana extends Vector {

	public RapidoMediana(int nElementos) {
		super(nElementos);
	}

	/**
	 * Calcula la mediana entre tres elementos en el vector: el primero, el último
	 * y el central Coloca estos tres elementos de tal forma que queden el menor la
	 * la izquierda, la mediana en el centro y el mayor la la derecha
	 * 
	 * @return la posición que ocupa la mediana entre estos tres elemenos
	 */
	private int getMediana3(int iz, int de, int cen) {
		// intercambiaremos los 3 elementos entre si para colocar la mediana en el medio
		if (elements[iz] > elements[cen])
			intercambiar(iz, cen);
		if (elements[iz] > elements[de])
			intercambiar(iz, de);
		if (elements[cen] > elements[de])
			intercambiar(cen, de);
		return cen;
	}

	/**
	 * Deja el pivote en una posicion tal que a su izquierda no hay ningún mayor,
	 * ni a la derecha ningun menor. Es un proceso lineal O(n).
	 */
	private int particion(int iz, int de) {

		int cen = getMediana3(iz, de, (iz + de) / 2);
		intercambiar(cen, iz);

		int pivote = elements[iz];
		int aux = iz;

		for (int i = iz + 1; i <= de; i++) {
			if (elements[i] <= pivote) {
				intercambiar(aux, i);
				aux++;
			}
		}

		intercambiar(iz, aux);
		return aux;
	}

	/**
	 * Ordenación por el método rápido (quicksort) Método divide y vencerás de
	 * complejidad estudiada en clase
	 */
	private void rapirec(int iz, int de) {
		if (de > iz) {
			int medio = particion(iz, de);
			rapirec(iz, medio - 1);
			rapirec(medio + 1, de);
		}
	}

	@Override
	public void ordenar() {
		rapirec(0, elements.length - 1);
	}

	@Override
	public String getNombre() {
		return "R�pido mediana a tres";
	}

}
