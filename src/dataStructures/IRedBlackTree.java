package dataStructures;

import java.util.ArrayList;

public interface IRedBlackTree<K extends Comparable<K>,V>  {
	public void leftRotate(NodeRBT<K,V> node);
	public void rightRotate(NodeRBT<K,V> node);
	public NodeRBT<K,V> search(NodeRBT<K,V> node, K key);
	public void insert(K key,V value);
	public void delete(K key);
	public ArrayList<NodeRBT<K,V>> inorderTraversal();
	
}
