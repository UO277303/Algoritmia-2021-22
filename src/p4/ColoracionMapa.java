package p4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ColoracionMapa {

	private HashMap<String, ArrayList<String>> paises;
	private HashMap<String, String> colores;
	private ArrayList<String> listaColores;
	private ArrayList<String> coloresUsados;

	public ColoracionMapa(String ficheroPaises, String ficheroColores) {
		paises = LeerFicheros.readPaises(ficheroPaises);
		listaColores = LeerFicheros.readColores(ficheroColores);
	}

	public void imprimirColoresUsados() {
		System.out.println("Se han utilizado " + coloresUsados.size() + " colores:");
		for (String c : coloresUsados) {
			System.out.println("\t" + c);
		}
	}

	public void imprimirPaisesYColores() {
		for (String p : paises.keySet()) {
			System.out.println(p + ": (" + colores.get(p) + ")");
			for (String f : paises.get(p)) {
				System.out.println("\t" + f + " (" + colores.get(p) + ")");
			}
		}
	}

	public void imprimirFronteras() {
		Set<String> p2 = paises.keySet();
		for (String p : p2) {
			System.out.println(p + ":");
			for (String f : paises.get(p)) {
				System.out.println("\t" + f);
			}
		}
		System.out.println("N� de pa�ses: " + paises.keySet().size());
	}

}
