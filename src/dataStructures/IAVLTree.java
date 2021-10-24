
package dataStructures;

import java.util.ArrayList;

public interface IAVLTree<K extends Comparable<K>, V> {
    public NodeAVL<K,V> search(NodeAVL<K,V> node, K key);
    public boolean insert(K key,V value);
    public void deleted(K key);
    public void rotateLeft(NodeAVL<K,V> node);
    public void rotateRight(NodeAVL<K,V> node);
    public int height(NodeAVL<K,V> node);
    public int GetBalancingFactor(NodeAVL<K,V> node);
    public ArrayList<V> showTree();
}
