package minmax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import graph.Graph;

public class MinMaxAlgorithm<T> {

	private Graph<NodeMinMaxValue<T>> minMaxTree;

	public MinMaxAlgorithm(Graph<NodeMinMaxValue<T>> minMaxTree) {
		this.minMaxTree = minMaxTree;
	}

	public List<NodeMinMaxValue<T>> execute(NodeMinMaxValue<T> rootNode,
			boolean isMaxOnTurn) {
		generateValue(rootNode, isMaxOnTurn);
		return generateMinMaxPath(rootNode, isMaxOnTurn);
	}

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

	private List<NodeMinMaxValue<T>> generateMinMaxPath(NodeMinMaxValue<T> rootNode,
			boolean isMaxOnTurn) {
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
