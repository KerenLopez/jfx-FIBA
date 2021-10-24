package dataStructures;

public class RedBlackTree<K extends Comparable<K>,V> implements IRedBlackTree<K,V> {

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

	@Override
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

	@Override
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

	@Override
	public NodeRBT<K, V> search(NodeRBT<K, V> node, K key) {
		NodeRBT<K,V> n= new NodeRBT<>(key,null);
		return searchNode(node,n);

	}

	private NodeRBT<K, V> searchNode(NodeRBT<K, V> node, NodeRBT<K, V> nSearched){
		if(node==null || node.compareTo(nSearched)==0) {
			return node;
		}else {
			if(node.compareTo(nSearched)>=0) {
				return searchNode(node.getLeft(), nSearched);
			}else{
				return searchNode(node.getRight(), nSearched);
			}
		}
	}

	@Override
	public boolean insert(K key, V value) {
		insertABB(key,value);
		return false;
	}

	@Override
	public void delete(K key) {
		deleteABB(key);

	}

	private void insertABB(K key,V value) {
		NodeRBT<K,V> newNode = new NodeRBT<>(key,value);
		NodeRBT<K,V> parent=null;
		NodeRBT<K,V> aux= root;
		while (aux != null) {
	        parent = aux;
	        if (newNode.compareTo(aux) < 0) {
	            aux = aux.getLeft();
	        }else
	            aux = aux.getRight();
	    }
		newNode.setParent(parent);
		if (parent == null) {
	        root = newNode;
		}else if (newNode.compareTo(parent) < 0) {
	        parent.setLeft(newNode); 
	    }else {
	    	parent.setRight(newNode); 
	    }

	}


	private void deleteABB(K key) {

	}


}
