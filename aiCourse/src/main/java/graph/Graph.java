package graph;

import java.util.List;

public interface Graph<T> {

	boolean existsNode(T node);

	List<T> getAllNodes();

	List<T> getNeighborNodes(T node);

	boolean addNode(T node, T parentNode);

	boolean deleteNode(T node);

}
