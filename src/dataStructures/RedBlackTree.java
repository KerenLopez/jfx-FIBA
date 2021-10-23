package dataStructures;

public class RedBlackTree<K,V> {

	private NodeRBT<K,V> root;

	public RedBlackTree() {
		root=null;
	}

	public NodeRBT<K,V> getRoot() {
		return root;
	}

	public void setRoot(NodeRBT<K,V> root) {
		this.root = root;
	}


	public void leftRotate(NodeRBT<K,V> node) {
		NodeRBT<K,V> n = node.getRight();

		node.setRight(n.getLeft());
		if (n.getLeft() != null) {

			n.getLeft().setParent(node);
		}
		n.setParent(node.getParent());
		if (node.getParent() == null) {
			root = n;
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(n);
		} else {
			node.getParent().setRight(n);
		}
		n.setLeft(node);
		node.setParent(n);

	}


	public void rightRotate(NodeRBT<K,V> node) {
		NodeRBT<K,V> n = node.getRight();

		node.setRight(n.getRight());
		if (n.getRight() != null) {
			n.getRight().setParent(node);
		}
		n.setParent(node.getParent());
		if (node.getParent() == null) {
			root = n;
		} else if (node == node.getParent().getRight()) {
			node.getParent().setRight(n);
		} else {
			node.getParent().setRight(n);
		}
		n.setRight(node);
		node.setParent(n);

	}


}
