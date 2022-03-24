package p4;

public class ColoracionMapaTiempos {

	public static void main(String[] args) {
		ColoracionMapa cm = new ColoracionMapa("fronteras.txt", "colores.txt");

		cm.coloracionMapa();

		System.out.println("PAISES:");
		cm.imprimirPaisesYColores();

		System.out.println("\nCOLORES:");
		cm.imprimirColoresUsados();
	}

}
