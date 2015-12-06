package minmax;

public class NodeMinMaxValue<T> {

	private int value;
	private T node;

	public NodeMinMaxValue(T node) {
		this.node = node;
		this.value = 0;
	}

	public NodeMinMaxValue(T node, int value) {
		this.value = value;
		this.node = node;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("[node=%s, value=%s]", node, value);
	}

}