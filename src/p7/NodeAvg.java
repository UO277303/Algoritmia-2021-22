package p7;

import java.util.ArrayList;

public class NodeAvg extends Node {

	private int depth;
	private Imagen[] dataset;
	private ArrayList<Integer> sol;

	public NodeAvg(Imagen[] imagenes, int depth, ArrayList<Integer> sol) {
		this.depth = depth;
		this.dataset = imagenes;
		this.sol = new ArrayList<Integer>(sol);
	}

	/**
	 * Si el nodo no es solución, el valor es el menor posible (-1) Si es solución,
	 * se calcula el zncc con la solución
	 */
	@Override
	public void calculateHeuristicValue() {
		if (isSolution()) {
			Imagen img1 = new Imagen(dataset[0].getWidth(), dataset[0].getHeight());
			Imagen img2 = new Imagen(dataset[0].getWidth(), dataset[0].getHeight());

			for (int i = 0; i < sol.size(); i++) {
				if (sol.get(i) == 1) {
					img1.addSignal(dataset[i]);
				} else if (sol.get(i) == 2) {
					img2.addSignal(dataset[i]);
				}
			}

			double zncc = img1.zncc(img2);
			this.heuristicValue = zncc * -1;
		} else {
			this.heuristicValue = -1;
		}
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> nodosHijos = new ArrayList<Node>();
		sol.add(0);
		nodosHijos.add(new NodeAvg(dataset, depth + 1, sol));
		sol.remove(sol.size() - 1);
		sol.add(1);
		nodosHijos.add(new NodeAvg(dataset, depth + 1, sol));
		sol.remove(sol.size() - 1);
		sol.add(2);
		nodosHijos.add(new NodeAvg(dataset, depth + 1, sol));
		sol.remove(sol.size() - 1);
		return nodosHijos;
	}

	@Override
	public boolean isSolution() {
		if (depth == dataset.length) {
			return true;
		}
		return false;
	}

}
