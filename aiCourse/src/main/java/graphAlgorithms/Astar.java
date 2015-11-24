package graphAlgorithms;

import graph.WeightedGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;

/**
 * This class represents A star algorithm. It finds the "optimal" path from one
 * node to another.
 * 
 * @author petar
 *
 * @param <T>
 */
public class Astar<T> {

	private static final int INITIAL_VALUE = 0;

	private WeightedGraph<T> graph;
	/**
	 * Heuristic function, used to determine what "optimal" means.
	 */
	private Function<T, Integer> heuristic;
	/**
	 * This list contains visited nodes, their parents and the total weight to
	 * get to this node. It is used at the end of the algorithm to generate the
	 * whole path from node A to node B.
	 */
	private List<VisitedNode<T>> visitedNodesInformation;

	public Astar(WeightedGraph<T> graph, Function<T, Integer> heuristic) {
		this.graph = graph;
		this.heuristic = heuristic;
		this.visitedNodesInformation = new ArrayList<VisitedNode<T>>();
	}

	public List<T> findBestPath(T startNode, T endNode) {
		Queue<NodeValue<T>> nodes = new PriorityQueue<NodeValue<T>>();
		NodeValue<T> nodeValue = new NodeValue<T>(startNode, INITIAL_VALUE);
		nodes.add(nodeValue);
		while (!nodes.isEmpty()) {
			nodeValue = nodes.poll();
			T currentNode = nodeValue.node;
			// System.out.println(currentNode + ", " + nodeValue.value);
			if (currentNode.equals(endNode)) {
				// found the path!
				System.out.println("Found path from: " + startNode + " to "
						+ endNode + " for " + nodeValue.value);
				return generateOverallPath(endNode);
			}
			addNeighborsInQueue(nodes, nodeValue, currentNode);
		}
		return null;
	}

	private void addNeighborsInQueue(Queue<NodeValue<T>> nodes,
			NodeValue<T> nodeValue, T currentNode) {
		for (T neighbor : graph.getNeighbors(currentNode)) {
			int value = nodeValue.value
					+ graph.getWeight(currentNode, neighbor)
					+ heuristic.apply(currentNode);
			nodes.add(new NodeValue<T>(neighbor, value));
			visitedNodesInformation.add(new VisitedNode<T>(neighbor,
					currentNode, value));
		}
	}

	private List<T> generateOverallPath(T endNode) {
		// sort visited nodes by weight
		visitedNodesInformation.sort(new Comparator<VisitedNode<T>>() {
			@Override
			public int compare(VisitedNode<T> o1, VisitedNode<T> o2) {
				return Integer.compare(o1.overallWeight, o2.overallWeight);
			}
		});
		List<T> path = new ArrayList<T>();
		path.add(endNode);
		boolean foundParent = true;
		T node = endNode;
		while (foundParent) {
			foundParent = false;
			for (VisitedNode<T> nodeToParentPath : visitedNodesInformation) {
				if (nodeToParentPath.node.equals(node)) {
					// visitedNodesInformation is sorted so the first occurrence
					// is "optimal"
					path.add(0, nodeToParentPath.parent);
					node = nodeToParentPath.parent;
					foundParent = true;
					break;
				}
			}
		}
		return path;
	}
}

/**
 * This is internal class used by the priority queue in the AStar algorithm. It
 * contains Node and his "value". "Value" is something that we determine. If the
 * nodes are cities, then value could be distance to these cities.(or it could
 * be some heuristic function, or in our case both)
 * 
 * @author petar
 *
 * @param <T>
 */
class NodeValue<T> implements Comparable<NodeValue<T>> {
	T node;
	int value;

	public NodeValue(T node, int value) {
		this.node = node;
		this.value = value;
	}

	public int compareTo(NodeValue<T> o) {
		return Integer.compare(this.value, o.value);
	}

}

/**
 * This is internal class used to help us generate the path at the end of the
 * algorithm. It contains node, his parent, and the overall weight to get to
 * this node
 * 
 * @author petar
 *
 * @param <T>
 */
class VisitedNode<T> {
	T node;
	T parent;
	int overallWeight;

	public VisitedNode(T node, T parent, int overallWeight) {
		this.node = node;
		this.parent = parent;
		this.overallWeight = overallWeight;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("(node=%s, parent=%s, %s)", node, parent,
				overallWeight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		VisitedNode<T> other = (VisitedNode<T>) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}
}
