package p2;

/**
 * Este programa sirve para ordenar n elementos con un algoritmo de los "malos"
 * (cuadrático)· es la SELECCION
 */
public class Seleccion extends Vector {
	public Seleccion(int nElementos) {
		super(nElementos);
	}

	/**
	 * Ordenación por selección
	 */
	@Override
	public void ordenar() {
		for (int i = 0; i < elements.length - 1; i++) {
			intercambiar(i, posMinimo(i, elements.length - 1));
		}
	}

	private int posMinimo(int first, int last) {
		int posMinimo = last;
		for (int i = first; i < last; i++) {
			if (elements[first] < elements[last]) {
				posMinimo = first;
			} else {
				posMinimo = last;
			}
		}
		return posMinimo;
	}

	@Override
	public String getNombre() {
		return "Selecci�n";
	}
}
