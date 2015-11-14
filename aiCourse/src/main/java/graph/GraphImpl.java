package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of graph, using adjacency table to represent nodes and edges.
 * The first element of each list is the node, and the elements after that are
 * the nodes that it is connected to. For example, a graph: (1,2), (1,3), (2,3)
 * will be represented as List{ List{1, 2, 3}, List{2,3} }
 * 
 * @author petar
 *
 * @param <T>
 *            - type of the nodes
 */
public class GraphImpl<T> implements Graph<T> {

	private List<List<T>> adjacencyTable;

	public GraphImpl() {
		adjacencyTable = new ArrayList<List<T>>();
	}

	public boolean existsNode(T node) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get all nodes in the graph.
	 * 
	 * return - List of all nodes.
	 */
	public List<T> getAllNodes() {
		List<T> allNodes = new ArrayList<T>();
		for (List<T> neighbors : adjacencyTable) {
			allNodes.add(neighbors.get(0));
		}
		return allNodes;
	}

	/**
	 * Get the Neighbors of a node.
	 * 
	 * return - List of all neighbors, or empty list if there aren't any
	 */
	public List<T> getNeighborNodes(T node) {
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(node)) {
				return new ArrayList<T>(neighbors.subList(1, neighbors.size()));
			}
		}
		return new ArrayList<T>();
	}

	/**
	 * Add node in the graph(Add edge between parentNode and node). If the
	 * parentNode does not exists, it is created.
	 * 
	 */
	public void addNode(T parentNode, T node) {
		createNodeIfMissing(parentNode);
		createNodeIfMissing(node);
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(parentNode)) {
				neighbors.add(node);
				return;
			}
		}
	}

	private void createNodeIfMissing(T parentNode) {
		if (!existsNode(parentNode)) {
			List<T> newNode = new ArrayList<T>();
			newNode.add(parentNode);
			adjacencyTable.add(newNode);
		}
	}

	/**
	 * Delete node.
	 */
	public void deleteNode(T node) {
		// remove the node and all edges that come from it
		for (List<T> neighbors : adjacencyTable) {
			if (neighbors.get(0).equals(node)) {
				adjacencyTable.remove(neighbors);
				break;
			}
		}
		// remove all edges that are connected to the node.
		for (List<T> neighbors : adjacencyTable) {
			List<T> nodeToRemove = new ArrayList<T>();
			nodeToRemove.add(node);
			neighbors.removeAll(nodeToRemove);
		}
	}

	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();
		for (List<T> nodes : adjacencyTable) {
			message.append(nodes.get(0));
			message.append(" : ");
			for (T node : nodes.subList(1, nodes.size())) {
				message.append(node + ", ");
			}
			// delete the last ','
			message.deleteCharAt(message.length() - 2);
			message.append(System.lineSeparator());
		}
		return message.toString();
	}

}
