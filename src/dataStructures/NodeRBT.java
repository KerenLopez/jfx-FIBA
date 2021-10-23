package dataStructures;

public class NodeRBT<K,V> {
	 private K key;
	 private V value;
     private NodeRBT<K,V> left;
     private NodeRBT<K,V> right;
     private char colour;
     private NodeRBT<K,V> parent;
     
     public NodeRBT(K k, V v) {
 		key=k;
 		value=v;
 		colour= 'B';
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
	
	public char getColour() {
		return colour;
	}
	
	public void setColour(char colour) {
		this.colour = colour;
	}
	
	public NodeRBT<K,V> getParent() {
		return parent;
	}
	
	public void setParent(NodeRBT<K,V> parent) {
		this.parent = parent;
	}

}
