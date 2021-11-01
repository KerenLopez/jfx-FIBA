
package dataStructures;

import java.util.ArrayList;

public interface IAVLTree<K extends Comparable<K>, V> {
    public NodeAVL<K,V> search(K key);
    public boolean insert(K key,V value);
    public boolean delete(K key);
    public NodeAVL<K,V> leftRotate(NodeAVL<K,V> node);
    public NodeAVL<K,V> rightRotate(NodeAVL<K,V> node);
    public ArrayList<NodeAVL<K,V>> inorderTraversal();
}
