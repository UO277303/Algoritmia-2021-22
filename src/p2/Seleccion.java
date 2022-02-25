package p2;

public class Seleccion extends Vector {

	public Seleccion(int nElementos) {
		super(nElementos);
	}

	@Override
	public void ordenar() {
		for (int i = 0; i < elements.length - 1; i++) {
			intercambiar(i, posMinimo(i, elements.length - 1));
		}
	}

	private int posMinimo(int i, int last) {
		int posMinimo = i;
		for (int j = i + 1; j < elements.length; j++) {
			if (elements[j] < elements[posMinimo]) {
				posMinimo = j;
			}
		}
		return posMinimo;
	}

	@Override
	public String getNombre() {
		return "Selección";
	}
}
