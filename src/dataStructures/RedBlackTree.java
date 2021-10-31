package dataStructures;

import java.util.ArrayList;

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
	public NodeRBT<K,V> getNil() {
		return nil;
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



		if (node.getParent() == null) {
			root = n;
		} else if (node == node.getParent().getLeft()) {
			node.getParent().setLeft(n);
		} else {
			node.getParent().setRight(n);
		}

		n.setLeft(node);
		if(n!=nil) {
			n.setParent(node.getParent());
		}
		node.setParent(n);

	}


	@Override
	public void rightRotate(NodeRBT<K,V> node) {
		NodeRBT<K,V> n = node.getLeft();

		node.setLeft(n.getRight());
		if (n.getRight() != nil) {

			n.getRight().setParent(node);
		}


		if (node.getParent() == null) {
			root = n;
		} else if (node == node.getParent().getRight()) {
			node.getParent().setRight(n);
		} else {
			node.getParent().setLeft(n);
		}

		n.setRight(node);


		if(n!=nil) {
			n.setParent(node.getParent());
		}
		node.setParent(n);


	}

	@Override
	public NodeRBT<K, V> search(NodeRBT<K, V> node, K key) {
		NodeRBT<K,V> n= new NodeRBT<>(key,null);
		if(searchNode(node,n)==nil) {
			return null;
		}else {
			return searchNode(node,n);
		}

	}

	private NodeRBT<K, V> searchNode(NodeRBT<K, V> node, NodeRBT<K, V> nSearched){
		if(node==nil || node.compareTo(nSearched)==0) {
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

		NodeRBT<K,V> node=search(root,key);
		if(node!=null) {
			//modification of the structure, due to the requirements of the problem
			NodeRBT<K,V> newNode = new NodeRBT<>(key,value);
			newNode.setLeft(node.getLeft());
			newNode.setRight(node.getRight());
			newNode.setParent(node.getParent());
			newNode.setColor(node.getColor());

			node.getSameKeyNodes().add(newNode);

		}else {


			node=insertABB(key,value);
			NodeRBT<K,V> uncle=null;


			while(node.getParent()!=null && node.getParent().getColor()=='R') {
				if(node.getParent().getParent()!=null &&node.getParent()==node.getParent().getParent().getRight()) {
					uncle=node.getParent().getParent().getLeft();
					if(uncle.getColor()=='R') {
						uncle.setColor('B');
						node.getParent().setColor('B');
						node.getParent().getParent().setColor('R');
						node=node.getParent().getParent();
					}else { 
						if(node==node.getParent().getLeft()) {
							node= node.getParent();
							rightRotate(node);
						}
						node.getParent().setColor('B');
						node.getParent().getParent().setColor('R');

						leftRotate(node.getParent().getParent());

					}

				}else if(node.getParent().getParent()!=null){
					uncle=node.getParent().getParent().getRight();
					if(uncle.getColor()=='R') {
						uncle.setColor('B');
						node.getParent().setColor('B');
						node.getParent().getParent().setColor('R');
						node=node.getParent().getParent();
					}else {
						if(node==node.getParent().getRight()) {

							node= node.getParent();
							leftRotate(node);
						}
						node.getParent().setColor('B');
						node.getParent().getParent().setColor('R');

						rightRotate(node.getParent().getParent());
					}


				}
				if(node==root) {
					break;
				}
			}
			root.setColor('B');
		}

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
			}else {
				aux = aux.getRight();
			}
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

		if(!node.getSameKeyNodes().isEmpty()) {
			//modification of the structure, due to the requirements of the problem
			NodeRBT<K,V> newHead=node.getSameKeyNodes().get(0);
			if(node.getParent()!=null ) {
				if(node.getParent().getLeft()==node) {
					node.getParent().setLeft(newHead);
				}else {
					node.getParent().setRight(newHead);
				}
			}
			
			if(node.getLeft()!=null) {
				node.getLeft().setParent(newHead);
			}
			
			if(node.getRight()!=null) {
				node.getLeft().setParent(newHead);
			}
			
			node.getSameKeyNodes().remove(0);
			newHead.setSameKeyNodes(node.getSameKeyNodes());
			
			
		}else {

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

			aux.setParent(temp.getParent());

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


	}

	private NodeRBT<K,V> successor(NodeRBT<K,V> node){
		NodeRBT<K,V> aux =null;

		if (node.getRight() != nil) {
			return minimum(node.getRight());
		}

		aux = node.getParent();

		while (aux != null && node == aux.getRight()) {
			node = aux;
			aux = aux.getParent();
		}

		return aux;

	}

	private NodeRBT<K,V> minimum(NodeRBT<K,V> node){
		while (node.getLeft() != nil) {
			node = node.getLeft();
		}

		return node;
	}

	private void deleteFixup(NodeRBT<K,V> node) {
		NodeRBT<K,V> aux=null;

		while (node != root && node.getColor() == 'B'){

			if (node == node.getParent().getLeft()){

				aux = node.getParent().getRight();

				// Case 1, aux's color is red.
				if (aux.getColor() == 'R'){
					aux.setColor('B');
					node.getParent().setColor('R');
					leftRotate(node.getParent());
					aux = node.getParent().getRight();
				}

				// Case 2, both of aux's children are black
				if (aux.getLeft().getColor() == 'B' &&	aux.getRight().getColor() == 'B'){
					aux.setColor('R');
					node = node.getParent();
				}
				// Case 3 / Case 4
				else{
					// Case 3, aux's right child is black
					if (aux.getRight().getColor() == 'B'){
						aux.getLeft().setColor('B');
						aux.setColor('R');
						rightRotate(aux);
						aux = node.getParent().getRight();
					}
					// Case 4, aux is black, aux.getRight() is red
					aux.setColor(node.getParent().getColor());
					node.getParent().setColor('B');
					aux.getRight().setColor('B');
					leftRotate(node.getParent());
					node = root;
				}
			}
			// exchange right and left
			else{

				aux = node.getParent().getLeft();

				// Case 1, aux's color is red.
				if (aux.getColor() == 'R'){
					aux.setColor('B');
					node.getParent().setColor('R');
					rightRotate(node.getParent());
					aux = node.getParent().getLeft();
				}

				// Case 2, both of aux's children are black
				if (aux.getRight().getColor() == 'B' &&	aux.getLeft().getColor() == 'B'){
					aux.setColor('R');
					node = node.getParent();
				}
				// Case 3 / Case 4
				else{
					// Case 3, aux's left child is black
					if (aux.getLeft().getColor() == 'B'){
						aux.getRight().setColor('B');
						aux.setColor('R');
						leftRotate(aux);
						aux = node.getParent().getLeft();
					}
					// Case 4, aux is black, aux.getLeft() is red
					aux.setColor(node.getParent().getColor());
					node.getParent().setColor('B');
					aux.getLeft().setColor('B');
					rightRotate(node.getParent());
					node = root;
				}
			}
		}

		node.setColor('B');
	}

	@Override
	public ArrayList<NodeRBT<K,V>> inorderTraversal() {
		ArrayList<NodeRBT<K,V>> nodes = new ArrayList<>();
		if(root!=nil) {
			inorderTraversal(root, nodes);
		}
		return nodes;
	}

	private void inorderTraversal(NodeRBT<K,V> node, ArrayList<NodeRBT<K,V>> nodes) {
		if(node!=nil) {
			inorderTraversal(node.getLeft(), nodes);
			nodes.add(node);
			inorderTraversal(node.getRight(), nodes);
		}
	}



}
