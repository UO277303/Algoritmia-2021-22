package p4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeerFicheros {

	public static HashMap<String, ArrayList<String>> readPaises(String file) {

		BufferedReader br = null;
		String line;
		HashMap<String, ArrayList<String>> paises = new HashMap<String, ArrayList<String>>();

		try {
			br = new BufferedReader(new FileReader(new File("files\\" + file)));
			line = br.readLine();

			while (line != null) {
				String[] dataLine = line.trim().split(":");
				if (!paises.containsKey(dataLine[0].trim())) {
					ArrayList<String> f = new ArrayList<String>();
					paises.putIfAbsent(dataLine[0].trim(), f);
				}

				String[] fronteras = dataLine[1].split(",");
				if (!fronteras[0].trim().equals("NO")) {
					for (String p : fronteras) {
						if (!paises.containsKey(p.trim())) {
							ArrayList<String> f = new ArrayList<String>();
							paises.putIfAbsent(p.trim(), f);
							paises.get(dataLine[0]).add(p.trim());
						} else {
							paises.get(dataLine[0]).add(p.trim());
						}
					}
				}

				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero '" + file + "' no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar el fichero '" + file + "'");
			}
		}

		return paises;
	}

	public static ArrayList<String> readColores(String file) {

		BufferedReader br = null;
		String line;
		ArrayList<String> colores = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(new File("files\\" + file)));
			line = br.readLine();

			while (line != null) {
				colores.add(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero '" + file + "' no existe");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar el fichero '" + file + "'");
			}
		}

		return colores;
	}

}
