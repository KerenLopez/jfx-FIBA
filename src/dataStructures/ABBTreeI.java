package dataStructures;

public interface ABBTreeI <K extends Comparable<K>, V>{
	public ABBNode<K,V> searchNode(ABBNode<K,V> node, K key);
	public boolean insertNode(K key,V value);
	public void deleteNode(K key);
	public String showTree();
}
