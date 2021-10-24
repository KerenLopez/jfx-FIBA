package dataStructures;

public class BSTNode <K extends Comparable<K>, V> implements Comparable<K>{
	 private K key;
	 private V value;
	 private BSTNode<K,V> left;
	 private BSTNode<K,V> right;
	 private BSTNode<K,V> parent;
	 
	 public BSTNode(K key, V value) {
		 this.key = key;
		 this.value = value; 
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
}
