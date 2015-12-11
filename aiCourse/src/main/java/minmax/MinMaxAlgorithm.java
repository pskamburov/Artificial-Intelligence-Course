package minmax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graph.Graph;

/**
 * Òhis class represents the MinMaxAlgorithm. Lets say we play a game, and we
 * have a tree of all possible states. We will find the optimal play,
 * considering both players are playing best possible way.
 * 
 * @author petar
 *
 * @param <T>
 */
public class MinMaxAlgorithm<T> {

	/**
	 * The tree of all possible states of the game.
	 */
	private Graph<NodeMinMaxValue<T>> minMaxTree;

	public MinMaxAlgorithm(Graph<NodeMinMaxValue<T>> minMaxTree) {
		this.minMaxTree = minMaxTree;
	}

	/**
	 * Generates the optimal play of both players.
	 * 
	 * @param rootNode
	 * @param isMaxOnTurn
	 * @return
	 */
	public List<NodeMinMaxValue<T>> execute(NodeMinMaxValue<T> rootNode,
			boolean isMaxOnTurn) {
		generateValue(rootNode, isMaxOnTurn);
		return generateMinMaxPath(rootNode, isMaxOnTurn);
	}

	/**
	 * Generates the values of all nodes of the tree.
	 * 
	 * @param rootNode
	 * @param isMaxTurn
	 * @return
	 */
	private int generateValue(NodeMinMaxValue<T> rootNode, boolean isMaxTurn) {
		List<NodeMinMaxValue<T>> children = minMaxTree
				.getNeighborNodes(rootNode);
		if (children.isEmpty()) {
			return rootNode.getValue();
		}
		List<Integer> childrenValues = new ArrayList<Integer>();
		for (NodeMinMaxValue<T> node : children) {
			childrenValues.add(generateValue(node, !isMaxTurn));
		}
		int value;
		if (isMaxTurn) {
			value = Collections.max(childrenValues);
		} else {
			value = Collections.min(childrenValues);
		}
		rootNode.setValue(value);
		return value;
	}

	/**
	 * Generates the best path for both players.
	 * 
	 * @param rootNode
	 * @param isMaxOnTurn
	 * @return
	 */
	private List<NodeMinMaxValue<T>> generateMinMaxPath(
			NodeMinMaxValue<T> rootNode, boolean isMaxOnTurn) {
		List<NodeMinMaxValue<T>> minMaxPath = new ArrayList<NodeMinMaxValue<T>>();
		NodeMinMaxValue<T> currentNode = rootNode;
		minMaxPath.add(currentNode);

		List<NodeMinMaxValue<T>> children = minMaxTree
				.getNeighborNodes(currentNode);
		Comparator<NodeMinMaxValue<T>> comparator = (NodeMinMaxValue<T> o1,
				NodeMinMaxValue<T> o2) -> Integer.compare(o1.getValue(),
				o2.getValue());

		while (!children.isEmpty()) {
			if (isMaxOnTurn) {
				currentNode = Collections.max(children, comparator);
			} else {
				currentNode = Collections.min(children, comparator);
			}
			minMaxPath.add(currentNode);
			isMaxOnTurn = !isMaxOnTurn;
			children = minMaxTree.getNeighborNodes(currentNode);
		}
		return minMaxPath;
	}
}
