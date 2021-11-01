package dataStructures;

import java.util.ArrayList;

public class BSTNode <K extends Comparable<K>, V> implements Comparable<K>{
	 private K key;
	 private V value;
	 private BSTNode<K,V> left;
	 private BSTNode<K,V> right;
	 private BSTNode<K,V> parent;
	//modification of the structure, due to the requirements of the problem
	 private ArrayList<BSTNode<K,V>> sameKeyNodes;
	 
	 public BSTNode(K key, V value) {
		 this.key = key;
		 this.value = value; 
		 sameKeyNodes = new ArrayList<>();
	 }

	public BSTNode<K,V> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K,V> left) {
		this.left = left;
	}

	public BSTNode<K,V> getRight() {
		return right;
	}

	public void setRight(BSTNode<K,V> right) {
		this.right = right;
	}

	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	@Override
	public int compareTo(K k) {
		return key.compareTo(k);
	}

	public BSTNode<K,V> getParent() {
		return parent;
	}

	public void setParent(BSTNode<K,V> parent) {
		this.parent = parent;
	}
	
	public String toString() {
		return value.toString();
	}

	public ArrayList<BSTNode<K, V>> getSameKeyNodes() {
		return sameKeyNodes;
	}

	public void setSameKeyNodes(ArrayList<BSTNode<K, V>> sameKeyNodes) {
		this.sameKeyNodes = sameKeyNodes;
	}
	
}
