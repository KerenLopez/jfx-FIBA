package dataStructures;

import java.util.ArrayList;

public interface BSTtreeI <K extends Comparable<K>, V>{
	public BSTNode<K,V> searchNode(K key);
	public void insertNode(K key,V value);
	public boolean deleteNode(K key);
	public ArrayList<BSTNode<K,V>> inorderTraversal();
}
