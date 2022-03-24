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
		colores = new HashMap<String, String>();
		coloresUsados = new ArrayList<String>();
	}

	public void coloracionMapa() {
		String[] paisesNombres = paises.keySet().toArray(new String[paises.size()]);

		añadirColor(paisesNombres[0], listaColores.get(0));
		for (int i = 1; i < paises.size(); i++) {
			añadirColor(paisesNombres[i], getColorPosible(paisesNombres[i]));
		}
	}

	private void añadirColor(String pais, String color) {
		colores.put(pais, color);
		if (!coloresUsados.contains(color)) {
			coloresUsados.add(color);
		}
	}

	private String getColorPosible(String pais) {
		ArrayList<String> coloresFronteras = new ArrayList<String>();
		for (int i = 0; i < paises.get(pais).size(); i++) {
			if (colores.get(paises.get(pais).get(i)) != null) {
				if (!coloresFronteras.contains(colores.get(paises.get(pais).get(i)))) {
					coloresFronteras.add(colores.get(paises.get(pais).get(i)));
				}
			}
		}

		for (int i = 0; i < listaColores.size(); i++) {
			if (!coloresFronteras.contains(listaColores.get(i))) {
				return listaColores.get(i);
			}
		}
		return "";
	}

	// Métodos para imprimir los datos por pantalla

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
				System.out.println("\t" + f + " (" + colores.get(f) + ")");
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
		System.out.println("Nº de países: " + paises.keySet().size());
	}

}
