package minmax;

import static org.junit.Assert.*;
import graph.Graph;
import graph.GraphImpl;

import java.util.ArrayList;
import java.util.List;

import minmax.MinMaxAlgorithm;
import minmax.NodeMinMaxValue;

import org.junit.BeforeClass;
import org.junit.Test;

public class MinMaxAlgorithmTest {
	private static final boolean IS_MAX_ON_TURN = true;
	private static final Graph<NodeMinMaxValue<Integer>> GRAPH = new GraphImpl<NodeMinMaxValue<Integer>>();
	private static final NodeMinMaxValue<Integer> root = new NodeMinMaxValue<Integer>(
			1);
	private static final NodeMinMaxValue<Integer> node2 = new NodeMinMaxValue<Integer>(
			2);
	private static final NodeMinMaxValue<Integer> node3 = new NodeMinMaxValue<Integer>(
			3);
	private static final NodeMinMaxValue<Integer> node4 = new NodeMinMaxValue<Integer>(
			4);
	private static final NodeMinMaxValue<Integer> node5 = new NodeMinMaxValue<Integer>(
			5, -4);
	private static final NodeMinMaxValue<Integer> node6 = new NodeMinMaxValue<Integer>(
			6, 1);
	private static final NodeMinMaxValue<Integer> node7 = new NodeMinMaxValue<Integer>(
			7, -5);
	private static final NodeMinMaxValue<Integer> node8 = new NodeMinMaxValue<Integer>(
			8, -2);
	private static final NodeMinMaxValue<Integer> node9 = new NodeMinMaxValue<Integer>(
			9, 3);

	@BeforeClass
	public static void initializeGraphs() {

		GRAPH.addNode(root, node2);
		GRAPH.addNode(root, node3);
		GRAPH.addNode(root, node4);
		GRAPH.addNode(node2, node5);
		GRAPH.addNode(node2, node6);
		GRAPH.addNode(node3, node7);
		GRAPH.addNode(node3, node8);
		GRAPH.addNode(node4, node9);

	}

	@Test
	public void testPathWhenMaxOnTurn() throws Exception {
		MinMaxAlgorithm<Integer> minMaxAlgorithm = new MinMaxAlgorithm<Integer>(
				GRAPH);
		List<NodeMinMaxValue<Integer>> minMaxPath = minMaxAlgorithm.execute(
				root, IS_MAX_ON_TURN);
		List<NodeMinMaxValue<Integer>> expectedPath = new ArrayList<NodeMinMaxValue<Integer>>();
		expectedPath.add(root);
		expectedPath.add(node4);
		expectedPath.add(node9);
		assertEquals(expectedPath, minMaxPath);
	}

	@Test
	public void testPathWhenMinOnTurn() throws Exception {
		MinMaxAlgorithm<Integer> minMaxAlgorithm = new MinMaxAlgorithm<Integer>(
				GRAPH);
		List<NodeMinMaxValue<Integer>> minMaxPath = minMaxAlgorithm.execute(
				root, !IS_MAX_ON_TURN);
		List<NodeMinMaxValue<Integer>> expectedPath = new ArrayList<NodeMinMaxValue<Integer>>();
		expectedPath.add(root);
		expectedPath.add(node3);
		expectedPath.add(node8);
		assertEquals(expectedPath, minMaxPath);
	}

	@Test
	public void testMaxValuesProperlySet() throws Exception {
		MinMaxAlgorithm<Integer> minMaxAlgorithm = new MinMaxAlgorithm<Integer>(
				GRAPH);
		minMaxAlgorithm.execute(root, IS_MAX_ON_TURN);
		assertEquals(3, root.getValue());
		assertEquals(3, node4.getValue());
		assertEquals(3, node9.getValue());
		assertEquals(-4, node2.getValue());
		assertEquals(-5, node3.getValue());
	}

	@Test
	public void testMinValuesProperlySet() throws Exception {
		MinMaxAlgorithm<Integer> minMaxAlgorithm = new MinMaxAlgorithm<Integer>(
				GRAPH);
		minMaxAlgorithm.execute(root, !IS_MAX_ON_TURN);
		assertEquals(-2, root.getValue());
		assertEquals(3, node4.getValue());
		assertEquals(3, node9.getValue());
		assertEquals(1, node2.getValue());
		assertEquals(-2, node3.getValue());
	}
}
