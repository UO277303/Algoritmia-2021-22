package p2;

public class OrdenacionPruebas {

	public static void main(String arg[]) {
//		int n = Integer.parseInt(arg[0]);
		int n = 9;

//		pruebaAlgoritmoOrdenacion(new Insercion(n));

//		pruebaAlgoritmoOrdenacion(new Seleccion(n));

//		pruebaAlgoritmoOrdenacion(new Burbuja(n));

//		pruebaAlgoritmoOrdenacion(new RapidoFatal(n));

//		pruebaAlgoritmoOrdenacion(new RapidoCentral(n));

		pruebaAlgoritmoOrdenacion(new RapidoMediana(n));
	}

	public static void pruebaAlgoritmoOrdenacion(Vector v) {
		System.out.println(" \n\nPrueba ordenación: " + v.getNombre());
		System.out.println("Ordenar Vector ya ordenado");
		v.ordenDirecto();
		System.out.println("Vector a ordenar es:");
		v.escribe(System.out);
		v.ordenar();
		System.out.println("Vector después de ordenar");
		v.escribe(System.out);

		System.out.println("Ordenar Vector inverso");
		v.ordenInverso();
		System.out.println("Vector a ordenar es:");
		v.escribe(System.out);
		v.ordenar();
		System.out.println("Vector después de ordenar");
		v.escribe(System.out);

		System.out.println("Ordenar Vector aleatorio");
		v.ordenAleatorio();
		System.out.println("Vector a ordenar es:");
		v.escribe(System.out);
		v.ordenar();
		System.out.println("Vector después de ordenar");
		v.escribe(System.out);
	}

}
