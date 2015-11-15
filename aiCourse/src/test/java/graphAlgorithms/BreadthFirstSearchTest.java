package graphAlgorithms;

import static org.junit.Assert.*;
import graph.Graph;
import graph.GraphImpl;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class BreadthFirstSearchTest {

	private static final Graph<Integer> GRAPH = new GraphImpl<Integer>();
	private static final Graph<Integer> UNDIRECTED_GRAPH = new GraphImpl<Integer>();

	@BeforeClass
	public static void initializeGraphs() {
		GRAPH.addNode(1, 2);
		GRAPH.addNode(1, 3);
		GRAPH.addNode(3, 4);
		GRAPH.addNode(3, 5);
		GRAPH.addNode(4, 1);

		UNDIRECTED_GRAPH.addNode(1, 2);
		UNDIRECTED_GRAPH.addNode(2, 1);
		UNDIRECTED_GRAPH.addNode(2, 3);
		UNDIRECTED_GRAPH.addNode(3, 2);
		UNDIRECTED_GRAPH.addNode(3, 5);
		UNDIRECTED_GRAPH.addNode(5, 3);
		UNDIRECTED_GRAPH.addNode(5, 4);
		UNDIRECTED_GRAPH.addNode(4, 5);

	}

	@Test
	public void testBfs() throws Exception {
		GraphAlgorithm<Integer> bfs = new BreadthFirstSearch<Integer>(GRAPH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 2, 3, 4, 5);

		assertEquals(expectedOrderOfNodes, bfs.execute(1));
		assertEquals(Arrays.asList(2), bfs.execute(2));
	}

	@Test
	public void testBfsOnUndirectedGraph() throws Exception {
		GraphAlgorithm<Integer> bfs = new BreadthFirstSearch<Integer>(
				UNDIRECTED_GRAPH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 2, 3, 5, 4);

		assertEquals(expectedOrderOfNodes, bfs.execute(1));
	}
}
