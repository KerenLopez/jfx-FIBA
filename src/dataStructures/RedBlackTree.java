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
		NodeRBT<K,V> n = node.getLeft();

		node.setLeft(n.getRight());
		if (n.getRight() != null) {

			n.getRight().setParent(node);
		}
		n.setParent(node.getParent());
		if (node.getParent() == null) {
			root = n;
		} else if (node == node.getParent().getRight()) {
			node.getParent().setRight(n);
		} else {
			node.getParent().setLeft(n);
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
	public void insert(K key, V value) {
		NodeRBT<K,V> node=insertABB(key,value);
		NodeRBT<K,V> uncle=null;

		while(node.getParent()!=null && node.getParent().getColor()=='R') {
			if(node.getParent().getParent()!=null &&node.getParent()==node.getParent().getParent().getRight()) {
				uncle=node.getParent().getParent().getLeft();
				if(uncle.getColor()=='R') {
					uncle.setColor('B');
					node.getParent().setColor('B');
					node.getParent().getParent().setColor('R');
					node=node.getParent().getParent();
				}else if(node==node.getParent().getLeft()) {
					node= node.getParent();
					leftRotate(node);

					node.getParent().setColor('B');
					node.getParent().getParent().setColor('R');

					rightRotate(node.getParent().getParent());
				}


			}else if(node.getParent().getParent()!=null &&node.getParent()==node.getParent().getParent().getLeft()) {
				uncle=node.getParent().getParent().getRight();
				if(uncle.getColor()=='R') {
					uncle.setColor('B');
					node.getParent().setColor('B');
					node.getParent().getParent().setColor('R');
					node=node.getParent().getParent();
				}else if(node==node.getParent().getRight()) {
					node= node.getParent();
					rightRotate(node);

					node.getParent().setColor('B');
					node.getParent().getParent().setColor('R');

					leftRotate(node.getParent().getParent());
				}


			}
		}
		root.setColor('B');

	}

	@Override
	public void delete(K key) {
		deleteABB(key);

	}

	private NodeRBT<K,V> insertABB(K key,V value) {
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
		return newNode;
	}


	private void deleteABB(K key) {
		NodeRBT<K,V> node = search(root,key);
		deleteNodeABB(node);
	}

	private void deleteNodeABB(NodeRBT<K,V> node) {
		if(node!=null) {
			//Case 1, The node is a leaf
			if(node.getLeft()==null && node.getRight()==null){
				if(node==root){
					root=null;
				}else if(node==node.getParent().getLeft()){
					node.getParent().setLeft(null);		
				}else{
					node.getParent().setRight(null);;
				}
				node.setParent(null);
			}else if(node.getLeft()==null || node.getRight()==null){
				//Case 2, The node has a child
				NodeRBT<K,V> onlyChild;
				if(node.getLeft()!=null){
					onlyChild=node.getLeft();
					node.setLeft(null);;
				}else{
					onlyChild=node.getRight();
					node.setRight(null);
				}
				onlyChild.setParent(node.getParent());
				if(node==root){
					root=onlyChild;
				}else if(node==node.getParent().getLeft()){
					node.getParent().setLeft(onlyChild);	
				}else{
					node.getParent().setRight(onlyChild);
				}
				node.setParent(null);
			}else{ 
				//Case 3, The node has two children
				NodeRBT<K,V> sucesor =min(node.getRight());
				node.setValue(sucesor.getValue());
				deleteNodeABB(sucesor);
			}
		}
	}

	private NodeRBT<K,V> min(NodeRBT<K, V> nodeRBT){
		if(nodeRBT.getLeft()!=null){
			return min(nodeRBT.getLeft());
		}else{
			return nodeRBT;
		}
	}


}
