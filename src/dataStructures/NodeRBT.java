package dataStructures;

public class NodeRBT<K extends Comparable<K>,V> implements Comparable<NodeRBT<K,V>> {
	 private K key;
	 private V value;
     private NodeRBT<K,V> left;
     private NodeRBT<K,V> right;
     private char color;// Red or black
     private NodeRBT<K,V> parent;
     
     public NodeRBT(K k, V v) {
 		key=k;
 		value=v;
 		color= 'R';
 		left=null;
 		right=null;
 		parent=null;
 	}
     
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public NodeRBT<K,V> getLeft() {
		return left;
	}
	
	public void setLeft(NodeRBT<K,V> left) {
		this.left = left;
	}
	
	public NodeRBT<K,V> getRight() {
		return right;
	}
	
	public void setRight(NodeRBT<K,V> right) {
		this.right = right;
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(char color) {
		this.color = color;
	}
	
	public NodeRBT<K,V> getParent() {
		return parent;
	}
	
	public void setParent(NodeRBT<K,V> parent) {
		this.parent = parent;
	}

	@Override
	public int compareTo(NodeRBT<K, V> n) {
		
		return key.compareTo(n.getKey());
	}

}
