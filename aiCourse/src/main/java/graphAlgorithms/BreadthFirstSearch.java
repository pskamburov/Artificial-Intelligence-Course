package graphAlgorithms;

import graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<T> implements GraphAlgorithm<T> {

	private Graph<T> graph;

	public BreadthFirstSearch(Graph<T> graph) {
		this.graph = graph;
	}

	public List<T> execute(T startingNode) {
		List<T> visitedNotes = new ArrayList<T>();
		Queue<T> nodesToVisit = new LinkedList<T>();
		nodesToVisit.add(startingNode);
		while ((startingNode = nodesToVisit.poll()) != null) {
			visitedNotes.add(startingNode);
			for (T neighborNode : graph.getNeighborNodes(startingNode)) {
				if (!visitedNotes.contains(neighborNode)) {
					nodesToVisit.add(neighborNode);
				}
			}
		}
		validateNodes(visitedNotes);
		return visitedNotes;
	}

	/**
	 * Print a message if there is a node that has not been visited. TODO:maybe
	 * throw some exception
	 * 
	 * @param visitedNotes
	 */
	private void validateNodes(List<T> visitedNotes) {
		if (visitedNotes.size() != graph.getAllNodes().size()) {
			System.err
					.println("Graph has disconnected component or a sink vertex");
		}
	}
}
