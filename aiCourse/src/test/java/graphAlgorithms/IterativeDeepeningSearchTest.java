package graphAlgorithms;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import graph.Graph;
import graph.GraphImpl;

import org.junit.BeforeClass;
import org.junit.Test;

public class IterativeDeepeningSearchTest {

	private static final int MAX_DEPTH = 4;
	private static final Graph<Integer> GRAPH = new GraphImpl<Integer>();
	private static final Graph<Integer> UNDIRECTED_GRAPH = new GraphImpl<Integer>();

	@BeforeClass
	public static void initializeGraphs() {
		GRAPH.addNode(1, 2);
		GRAPH.addNode(1, 3);
		GRAPH.addNode(3, 4);
		GRAPH.addNode(3, 5);
		GRAPH.addNode(4, 1);
		GRAPH.addNode(5, 6);

		UNDIRECTED_GRAPH.addNode(1, 2);
		UNDIRECTED_GRAPH.addNode(2, 1);
		UNDIRECTED_GRAPH.addNode(2, 3);
		UNDIRECTED_GRAPH.addNode(3, 2);
		UNDIRECTED_GRAPH.addNode(3, 5);
		UNDIRECTED_GRAPH.addNode(3, 6);
		UNDIRECTED_GRAPH.addNode(5, 3);
		UNDIRECTED_GRAPH.addNode(5, 4);
		UNDIRECTED_GRAPH.addNode(4, 5);
		UNDIRECTED_GRAPH.addNode(4, 6);
		UNDIRECTED_GRAPH.addNode(6, 3);
		UNDIRECTED_GRAPH.addNode(6, 4);
	}

	@Test
	public void test() {
		GraphAlgorithm<Integer> iddfs = new IterativeDeepeningSearch<Integer>(
				GRAPH, MAX_DEPTH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 3, 5, 6, 4, 2);

		assertEquals(expectedOrderOfNodes, iddfs.execute(1));
	}

	@Test
	public void testIddfsOnUnderictedGraph() throws Exception {
		GraphAlgorithm<Integer> iddfs = new IterativeDeepeningSearch<Integer>(
				UNDIRECTED_GRAPH, MAX_DEPTH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 2, 3, 6, 5);

		assertEquals(expectedOrderOfNodes, iddfs.execute(1));
	}

}
