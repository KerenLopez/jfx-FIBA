package dataStructures;


public class ABBTree <K extends Comparable<K>, V> implements ABBTreeI<K,V> {

	private ABBNode<K,V> root;
	private String treeStr;

	//CrearArbol
	public ABBTree() {

	}

	public ABBNode<K,V> getRoot() {
		return root;
	}

	public void setRoot(ABBNode<K,V> root) {
		this.root = root;
	}

	public String SearchNodeString(K key) {
		ABBNode<K,V> founded = searchNode(root,key);
		String nodeStr = "null";
		if(founded!=null) {
			nodeStr = founded.toString();
		}
		return nodeStr;
	}

	@Override
	public ABBNode<K,V> searchNode(ABBNode<K,V> node, K key) {
		if(node==null || node.compareTo(key)==0) {
			return node;
		}else {
			if(node.compareTo(key)>=0) {
				return searchNode(node.getLeft(), key);
			}else{
				return searchNode(node.getRight(), key);
			}
		}
	}

	@Override
	public boolean insertNode(K key, V value) {
		ABBNode<K,V> newNode = new ABBNode<K,V>(key,value);
		boolean inserted = false;
		if(root==null) {
			root = newNode;
		}else {
			insertNodeRecursive(root, newNode);
			inserted = true;
		}
		return inserted;
	}

	private void insertNodeRecursive(ABBNode<K,V> node, ABBNode<K,V> newNode) {
		if(newNode.compareTo(node.getKey())<0){
			if(node.getLeft()==null){
				node.setLeft(newNode);
				newNode.setParent(node);
			}else{
				insertNodeRecursive(node.getLeft(),newNode);
			}
		}else{
			if(node.getRight()==null){
				node.setRight(newNode);
				newNode.setParent(node);
			}else{
				insertNodeRecursive(node.getRight(),newNode);
			}	
		}
	}

	@Override
	public void deleteNode(K key){
		ABBNode<K,V> node = searchNode(root,key);
		deleteNodeRecursive(node);
	}
	
	private void deleteNodeRecursive(ABBNode<K,V> node) {
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
					ABBNode<K,V> onlyChild;
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
					ABBNode<K,V> sucesor =min(node.getRight());
					node.setValue(sucesor.getValue());
					deleteNodeRecursive(sucesor);
				}
			}
	}

	private ABBNode<K,V> min(ABBNode<K,V> node){
		if(node.getLeft()!=null){
			return min(node.getLeft());
		}else{
			return node;
		}
	}

	@Override
	public String showTree() {
		treeStr = "";
		if(root!=null) {
			inorderTraversal(root);
		}
		return treeStr;
	}

	private void inorderTraversal(ABBNode<K,V> node) {
		if(node!=null) {
			inorderTraversal(node.getLeft());
			treeStr+=node.toString()+" ";
			inorderTraversal(node.getRight());
		}
	}
}
