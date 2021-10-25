
package dataStructures;

import java.util.ArrayList;

public interface IAVLTree<K extends Comparable<K>, V> {
    public NodeAVL<K,V> search(K key);
    public boolean insert(K key,V value);
    public boolean deleted(K key);
    public NodeAVL<K,V> rotateLeft(NodeAVL<K,V> node);
    public NodeAVL<K,V> rotateRight(NodeAVL<K,V> node);
    public int height(NodeAVL<K,V> node);
    public int GetBalancingFactor(NodeAVL<K,V> node);
    public ArrayList<V> showTree();
}
