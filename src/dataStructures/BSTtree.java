package dataStructures;

import java.util.ArrayList;

public class BSTtree <K extends Comparable<K>, V> implements BSTtreeI<K,V> {

	private BSTNode<K,V> root;
	
	//Create tree
	public BSTtree() {

	}

	public BSTNode<K,V> getRoot() {
		return root;
	}

	public void setRoot(BSTNode<K,V> root) {
		this.root = root;
	}

	@Override
	public void insertNode(K key, V value) {
		BSTNode<K,V> newNode = new BSTNode<K,V>(key,value);
		if(root==null) {
			root = newNode;
		}else {
			insertNodeRecursive(root, newNode);
		}
	}

	private void insertNodeRecursive(BSTNode<K,V> node, BSTNode<K,V> newNode) {
		if(newNode.compareTo(node.getKey())<=0){
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
	public BSTNode<K,V> searchNode(K key) {
		BSTNode<K,V> founded = searchNodeRecursive(root,key);
		return founded;
	}
	
	private BSTNode<K,V> searchNodeRecursive(BSTNode<K,V> node, K key) {
		if(node==null || node.compareTo(key)==0) {
			return node;
		}else {
			if(node.compareTo(key)>=0) {
				return searchNodeRecursive(node.getLeft(), key);
			}else{
				return searchNodeRecursive(node.getRight(), key);
			}
		}
	}

	@Override
	public boolean deleteNode(K key){
		Boolean deleted = false;
		BSTNode<K,V> founded = searchNode(key);
		if(founded!=null) {
			deleteNodeRecursive(founded);
			deleted = true;
		}
		return deleted;
	}
	
	private void deleteNodeRecursive(BSTNode<K,V> node) {
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
					BSTNode<K,V> onlyChild;
					if(node.getLeft()!=null){
						onlyChild=node.getLeft();
						node.setLeft(null);
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
					BSTNode<K,V> sucesor =min(node.getRight());
					node.setValue(sucesor.getValue());
					node.setKey(sucesor.getKey());
					deleteNodeRecursive(sucesor);
				}
			}
	}

	private BSTNode<K,V> min(BSTNode<K,V> node){
		if(node.getLeft()!=null){
			return min(node.getLeft());
		}else{
			return node;
		}
	}

	@Override
	public ArrayList<BSTNode<K,V>> inorderTraversal() {
		ArrayList<BSTNode<K,V>> nodes = new ArrayList<BSTNode<K,V>>();
		if(root!=null) {
			inorderTraversal(root, nodes);
		}
		return nodes;
	}

	private void inorderTraversal(BSTNode<K,V> node, ArrayList<BSTNode<K,V>> nodes) {
		if(node!=null) {
			inorderTraversal(node.getLeft(), nodes);
			nodes.add(node);
			inorderTraversal(node.getRight(), nodes);
		}
	}
}
