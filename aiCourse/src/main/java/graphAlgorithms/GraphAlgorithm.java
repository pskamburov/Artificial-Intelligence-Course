package graphAlgorithms;

import java.util.List;

public interface GraphAlgorithm<T> {

	List<T> execute(T startingNode);
}
