package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphImpl<T> implements Graph<T> {

	private List<List<T>> adjacencyTable;

	public boolean existsNode(T node) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.contains(node)) {
				return true;
			}
		}
		return false;
	}

	public List<T> getAllNodes() {
		List<T> allNodes = new ArrayList<T>();
		for (List<T> neighbors : adjacencyTable) {
			allNodes.add(neighbors.get(0));
		}
		return allNodes;
	}

	public List<T> getNeighborNodes(T node) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(node)) {
				return new ArrayList<T>(neighbors.subList(1, neighbors.size()));
			}
		}
		return null;
	}

	public boolean addNode(T node, T parentNode) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(parentNode)) {
				neighbors.add(node);
				return true;
			}
		}
		return false;
	}

	public boolean deleteNode(T node) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(node)) {
				neighbors.remove(node);
				return true;
			}
		}
		return false;
	}

}
