package p2;

public class Burbuja extends Vector {

	public Burbuja(int nElementos) {
		super(nElementos);
	}

	@Override
	public void ordenar() {
		for (int i = 0; i < elements.length - 1; i++) {
			for (int j = elements.length - 1; j > i; j--) {
				if (elements[j - 1] > elements[j]) {
					intercambiar(j - 1, j);
				}
			}
		}
	}

	@Override
	public String getNombre() {
		return "Burbuja";
	}
}