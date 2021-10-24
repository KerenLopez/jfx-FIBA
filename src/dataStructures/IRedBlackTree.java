package dataStructures;

public interface IRedBlackTree<K extends Comparable<K>,V>  {
	public void leftRotate(NodeRBT<K,V> node);
	public void rightRotate(NodeRBT<K,V> node);
	public NodeRBT<K,V> search(NodeRBT<K,V> node, K key);
	public boolean insert(K key,V value);
	public void delete(K key);
	
}
