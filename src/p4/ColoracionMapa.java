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

		int indexColor = 0;
		añadirColor(paisesNombres[0], listaColores.get(indexColor));

		for (int i = 1; i < paises.size(); i++) {
			for (String front : paises.get(paisesNombres[i])) {
				while (getColorDePais(front).equals(getColorDePais(paisesNombres[i]))) {
					indexColor++;
				}
				añadirColor(paisesNombres[i], listaColores.get(indexColor));
				indexColor = 0;
			}
		}
	}

	private void añadirColor(String pais, String color) {
		if (colores.containsKey(pais)) {
			colores.put(pais, color);
		} else {
			colores.putIfAbsent(pais, color);
		}

		if (!coloresUsados.contains(color)) {
			coloresUsados.add(color);
		}
	}

	private String getColorDePais(String pais) {
		if (!colores.containsKey(pais)) {
			return "";
		}
		return colores.get(pais);
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
		System.out.println("Nº de países: " + paises.keySet().size());
	}

}
