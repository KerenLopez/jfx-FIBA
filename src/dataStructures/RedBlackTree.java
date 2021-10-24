package dataStructures;

public class RedBlackTree<K extends Comparable<K>,V> implements IRedBlackTree<K,V> {

	private NodeRBT<K,V> root;
	private NodeRBT<K,V> nil;

	public RedBlackTree() {
		
		nil= new NodeRBT<>(null, null);
		nil.setColor('B');
		root=nil;
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
		if (n.getLeft() != nil) {

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
		if (n.getRight() != nil) {

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


	private NodeRBT<K,V> insertABB(K key,V value) {
		NodeRBT<K,V> newNode = new NodeRBT<>(key,value);
		newNode.setLeft(nil);
		newNode.setRight(nil);
		NodeRBT<K,V> parent=null;
		NodeRBT<K,V> aux= root;
		while (aux != nil) {
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

	

	@Override
	public void delete(K key) {
		NodeRBT<K,V> node = search(root,key);
		NodeRBT<K,V> aux=nil;
		NodeRBT<K,V> temp=nil;
		
		if(node.getLeft()==nil || node.getRight()==nil) {
			temp=node;
		}else {
			temp= successor(node);
		}
		
		if(temp.getLeft()!=nil) {
			aux=temp.getLeft();
		}else {
			aux=temp.getRight();
		}
		
		if(temp.getParent()==null) {
			root=aux;
		}else if(temp.getParent().getLeft()!=nil && temp.getParent().getLeft()==temp) {
			temp.getParent().setLeft(aux);
		
		}else if(temp.getParent().getRight()!=nil && temp.getParent().getRight()==temp) {
			temp.getParent().setRight(aux);
		}
		
		if(temp!=node) {
			node.setKey(temp.getKey());
			node.setValue(temp.getValue());
		}
		
		if(temp.getColor()=='B') {
			deleteFixup(aux);
		}
				
		

	}
	
	private NodeRBT<K,V> successor(NodeRBT<K,V> node){
		return null;
	}
	
	private void deleteFixup(NodeRBT<K,V> node) {
		
	}



	


}
