package graph;

import java.util.List;

public interface Graph<T> {

	boolean existsNode(T node);

	List<T> getAllNodes();

	List<T> getNeighborNodes(T node);

	void addNode(T node, T parentNode);

	void deleteNode(T node);

}
