package dataStructures;

public class ABBNode <K extends Comparable<K>, V> implements Comparable<K>{
	 private K key;
	 private V value;
	 private ABBNode<K,V> left;
	 private ABBNode<K,V> right;
	 private ABBNode<K,V> parent;
	 
	 public ABBNode(K key, V value) {
		 this.key = key;
		 this.value = value; 
	 }

	public ABBNode<K,V> getLeft() {
		return left;
	}

	public void setLeft(ABBNode<K,V> left) {
		this.left = left;
	}

	public ABBNode<K,V> getRight() {
		return right;
	}

	public void setRight(ABBNode<K,V> right) {
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

	public ABBNode<K,V> getParent() {
		return parent;
	}

	public void setParent(ABBNode<K,V> parent) {
		this.parent = parent;
	}
	
	public String toString() {
		return value.toString();
	}
}
