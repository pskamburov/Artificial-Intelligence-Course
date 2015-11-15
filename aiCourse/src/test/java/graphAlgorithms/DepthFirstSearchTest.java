package graphAlgorithms;

import static org.junit.Assert.assertEquals;
import graph.Graph;
import graph.GraphImpl;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class DepthFirstSearchTest {

	private static final int STARTING_NODE = 1;
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
		UNDIRECTED_GRAPH.addNode(3, 6);
		UNDIRECTED_GRAPH.addNode(5, 3);
		UNDIRECTED_GRAPH.addNode(5, 4);
		UNDIRECTED_GRAPH.addNode(4, 5);
		UNDIRECTED_GRAPH.addNode(4, 6);
		UNDIRECTED_GRAPH.addNode(6, 3);
		UNDIRECTED_GRAPH.addNode(6, 4);

	}

	@Test
	public void testDfs() throws Exception {
		GraphAlgorithm<Integer> bfs = new DepthFirstSearch<Integer>(GRAPH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 3, 5, 4, 2);

		assertEquals(expectedOrderOfNodes, bfs.execute(STARTING_NODE));
		assertEquals(Arrays.asList(2), bfs.execute(2));
	}

	@Test
	public void testDfsOnUndirectedGraph() throws Exception {
		GraphAlgorithm<Integer> bfs = new DepthFirstSearch<Integer>(
				UNDIRECTED_GRAPH);
		List<Integer> expectedOrderOfNodes = Arrays.asList(1, 2, 3, 6, 4, 5);

		assertEquals(expectedOrderOfNodes, bfs.execute(STARTING_NODE));
	}
}
