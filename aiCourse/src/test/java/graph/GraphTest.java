package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	private static final Graph<Integer> GRAPH = new GraphImpl<Integer>();

	@Before
	public void setUpGraph() {
		GRAPH.addNode(1, 2);
		GRAPH.addNode(1, 3);
		GRAPH.addNode(3, 4);
		GRAPH.addNode(3, 5);
		GRAPH.addNode(4, 1);
	}

	@Test
	public void testInitialization() throws Exception {

		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1,
				2, 3, 4, 5));

		assertEquals(expectedNodes, GRAPH.getAllNodes());
	}

	@Test
	public void testGetNeighborNodes() throws Exception {
		assertEquals(new ArrayList<Integer>(Arrays.asList(2, 3)),
				GRAPH.getNeighborNodes(1));
		assertEquals(new ArrayList<Integer>(Arrays.asList(4, 5)),
				GRAPH.getNeighborNodes(3));
		assertTrue(GRAPH.getNeighborNodes(2).isEmpty());
	}

	@Test
	public void testDeleteNode() throws Exception {
		GRAPH.deleteNode(1);

		assertTrue(GRAPH.getNeighborNodes(1).isEmpty());
		assertTrue(GRAPH.getNeighborNodes(4).isEmpty());
	}

}
