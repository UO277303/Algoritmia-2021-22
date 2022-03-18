package p4;

public class ColoracionMapaTiempos {

	public static void main(String[] args) {
		ColoracionMapa cm = new ColoracionMapa("fronteras.txt", "colores.txt");

		System.out.println("PAISES:");
		cm.imprimirFronteras();
	}

}
