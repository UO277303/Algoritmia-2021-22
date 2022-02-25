package p2;

public class Insercion extends Vector {

	public Insercion(int nElementos) {
		super(nElementos);
	}

	@Override
	public void ordenar() {
		int current = 0;
		for (int i = 0; i < elements.length; i++) {
			current = elements[i];
			int j = i - 1;
			while (j >= 0 && current < elements[j]) {
				elements[j + 1] = elements[j];
				j--;
			}
			elements[j + 1] = current;
		}
	}

	@Override
	public String getNombre() {
		return "Inserci�n directa";
	}
}
