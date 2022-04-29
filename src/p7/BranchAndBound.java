package p7;

import java.util.ArrayList;

/**
 * Main class to solve problems using the Branch and Bound technique We need to
 * extend it for any specific problem
 */
public abstract class BranchAndBound {
	protected Heap ds; // Nodes to be explored (not used nodes)
	protected Node bestNode; // To save the final node of the best solution
	protected Node rootNode; // Initial node
	protected double pruneLimit; // To prune nodes above this value
	protected int nodosProcesados = 0;
	protected int nodosGenerados = 0;
	protected int nodosPodados = 0;

	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); // We create an instance of the Heap class to save the nodes
	}

	/**
	 * Manages all the process, from the beginning to the end
	 * 
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) {
		ds.insert(rootNode); // First node to be explored

		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();
			nodosProcesados++;

			ArrayList<Node> children = node.expand();
			nodosGenerados += children.size();

			for (Node child : children) {
				if (child.isSolution()) {
					double cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} else {
						nodosPodados++;
					}
				} else if (child.getHeuristicValue() < pruneLimit) {
					ds.insert(child);
				}
			}

		} // while
	}

	/**
	 * Gets the root node
	 * 
	 * @return The root node
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * Gets the best node
	 * 
	 * @return The best node
	 */
	public Node getBestNode() {
		return bestNode;
	}

}
