package graphAlgorithms;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeDeepeningSearch<T> implements GraphAlgorithm<T> {
	
	private Graph<T> graph;
	private int depth;

	public IterativeDeepeningSearch(Graph<T> graph, int depth) {
		this.graph = graph;
		setDepth(depth);
	}

	public List<T> execute(T startNode) {
		List<T> visitedNodes = null;
		for (int i = 1; i <= depth; i++) {
			System.out.println(String.format("------Depth:%d------", i));
			visitedNodes = limitedDepthDfs(startNode, i);
			System.out.println("");
		}
		return visitedNodes;
	}

	List<T> limitedDepthDfs(T startNode, int depth) {
		int currentDepth = 0;
		List<T> visitedNodes = new ArrayList<T>();
		Stack<T> nodesToVisit = new Stack<T>();
		nodesToVisit.push(startNode);

		while (!nodesToVisit.isEmpty()) {
			startNode = nodesToVisit.pop();
			if (visitedNodes.contains(startNode)) {
				continue;
			}
			System.out.print(String.format("%s\t", startNode));
			visitedNodes.add(startNode);
			if ((currentDepth + 1) >= depth) {
				continue;
			} else {
				currentDepth++;
				List<T> neighbors = graph.getNeighborNodes(startNode);
				for (T neighbor : neighbors) {
					nodesToVisit.push(neighbor);
				}
			}

		}
		return visitedNodes;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
