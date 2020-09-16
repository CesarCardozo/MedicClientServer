package structure;


public class NodeAvl<T> {
	private T info;
	private NodeAvl<T> left;
	private NodeAvl<T> right;
	protected int fe;

	public NodeAvl(T data) {
		left = null;
		this.info = data;
		right = null;
		fe = 0;
	}

	public NodeAvl(NodeAvl<T> brenchLeft, T data, NodeAvl<T> brenchRight) {
		left = brenchLeft;
		this.info = data;
		right = brenchRight;
		fe = 0;
	}

	/**
	 * @return data of node
	 */
	public T valueNode() {
		return info;
	}

	/**
	 * @param dato the dato to set
	 */
	public void setValue(T dato) {
		this.info = dato;
	}

	/**
	 * @return the izquierda
	 */
	public NodeAvl<T> subTreeLeft() {
		return left;
	}

	/**
	 * @param left the izquierda to set
	 */
	public void branchLeft(NodeAvl<T> left) {
		this.left = left;
	}

	/**
	 * @return the derecha
	 */
	public NodeAvl<T> subTreeRigth() {
		return right;
	}

	/**
	 * @param rigth the derecha to set
	 */
	public void branchRigth(NodeAvl<T> rigth) {
		this.right = rigth;
	}
	
	public T getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return "[dato=" + info + ", fe=" + fe + "]";
	}	
}