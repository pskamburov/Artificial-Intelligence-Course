package graphAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import graph.Graph;

public class DepthFirstSearch<T> implements GraphAlgorithm<T> {
	private Graph<T> graph;

	public DepthFirstSearch(Graph<T> graph) {
		this.graph = graph;
	}

	public List<T> execute(T startNode) {
		List<T> visitedNodes = new ArrayList<T>();
		Stack<T> nodesToVisit = new Stack<T>();
		nodesToVisit.push(startNode);

		while (!nodesToVisit.isEmpty()) {
			startNode = nodesToVisit.pop();
			if (visitedNodes.contains(startNode)) {
				continue;
			}
			visitedNodes.add(startNode);

			List<T> neighbors = graph.getNeighborNodes(startNode);
			for (T neighbor : neighbors) {
				nodesToVisit.push(neighbor);
			}

		}
		return visitedNodes;

	}

}
