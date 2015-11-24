package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * This is internal class that represents the edge of the graphs. It contains
 * two vertexes of the graph and the weight between them.
 * 
 * @author petar
 *
 * @param <T>
 */
class Edge<T> {

	private T fromVertex;
	private T toVertex;
	private int weight;

	public Edge(T fromVertex, T toVertex, int weight) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public T getFromVertex() {
		return fromVertex;
	}

	public void setFromVertex(T fromVertex) {
		this.fromVertex = fromVertex;
	}

	public T getToVertex() {
		return toVertex;
	}

	public void setToVertex(T toVertex) {
		this.toVertex = toVertex;
	}

}

public class WeightedGraph<T> {

	private List<Edge<T>> graphEdges;

	public WeightedGraph() {
		graphEdges = new ArrayList<Edge<T>>();
	}

	public void addNode(T fromNode, T toNode, int weight) {
		graphEdges.add(new Edge<T>(fromNode, toNode, weight));
	}

	public void deleteNode(T node) {
		List<Edge<T>> edgesToDelete = new ArrayList<Edge<T>>();
		for (Edge<T> edge : graphEdges) {
			if (edge.getToVertex().equals(node)) {
				edgesToDelete.add(edge);
			}
		}
		graphEdges.removeAll(edgesToDelete);
	}

	public List<T> getNeighbors(T fromNode) {
		List<T> neighbors = new ArrayList<T>();
		for (Edge<T> edge : graphEdges) {
			if (edge.getFromVertex().equals(fromNode)) {
				neighbors.add(edge.getToVertex());
			}
		}
		return neighbors;
	}

	/**
	 * Get the weight of the edge between fromNode and toNode
	 * 
	 * @param fromNode
	 * @param toNode
	 * @return - the weight, or -1 if there is no edge between fromNode and
	 *         toNode
	 */
	public int getWeight(T fromNode, T toNode) {
		for (Edge<T> edge : graphEdges) {
			if (edge.getFromVertex().equals(fromNode)
					&& edge.getToVertex().equals(toNode)) {
				return edge.getWeight();
			}
		}
		return -1;
	}

}
