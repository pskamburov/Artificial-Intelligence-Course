package graphAlgorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import graph.WeightedGraph;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class AstarTest {

	private static final WeightedGraph<String> GRAPH = new WeightedGraph<String>();

	@BeforeClass
	public static void initializeGraphs() {
		GRAPH.addNode("Washington", "New York", 1);
		GRAPH.addNode("Washington", "Austin", 1);
		GRAPH.addNode("Austin", "Miami", 1);
		GRAPH.addNode("Miami", "Las Vegas", 1);
		GRAPH.addNode("Las Vegas", "Los Angeles", 1);
		GRAPH.addNode("New York", "Chicago", 1);
		GRAPH.addNode("New York", "Florida", 1);
		GRAPH.addNode("New York", "Philadelphia", 1);
		GRAPH.addNode("New York", "Jackson", 1);
		GRAPH.addNode("Florida", "Los Angeles", 1);

	}

	/**
	 * Lets say we have to fly over America. We have a graph with the time(in
	 * hours) to fly between two airports. We want to fly from Washington to Los
	 * Angeles, as fast as possible. But we know that there is traffic! So the
	 * more connected is the city, the longer it takes to catch on the
	 * plane.(this is our heuristic - more neighbors == more time to travel)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAstar() throws Exception {
		Function<String, Integer> heuristic = (node) -> GRAPH
				.getNeighbors(node).size() * 5;
		Astar<String> aStarAlgorithm = new Astar<String>(GRAPH, heuristic);
		List<String> path = Arrays.asList("Washington", "Austin", "Miami",
				"Las Vegas", "Los Angeles");

		assertEquals(path,
				aStarAlgorithm.findBestPath("Washington", "Los Angeles"));

	}

	@Test
	public void testNoPathExists() throws Exception {
		Astar<String> aStarAlgorithm = new Astar<String>(GRAPH, x -> 0);
		assertNull(aStarAlgorithm.findBestPath("New York", "Miami"));
	}
}
