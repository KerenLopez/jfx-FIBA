
package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;

public class NodeAVL <K extends Comparable<K>,V> implements Comparable<K>, Serializable {

	private static final long serialVersionUID = 1;
    
    private K key;
    private V value;
    private int height;
    private NodeAVL<K,V> left;
    private NodeAVL<K,V> right;
    private NodeAVL<K,V> parent;
    private ArrayList<NodeAVL<K,V>> sameKeyNodes;
    
    public NodeAVL(K key, V value) {
        this.key = key;
        this.value = value;
        left=null;
        right=null;
        parent=null;
        height=1;
       sameKeyNodes=new ArrayList<>();
    }

    public ArrayList<NodeAVL<K, V>> getSameKeyNodes() {
        return sameKeyNodes;
    }

    public void setSameKeyNodes(ArrayList<NodeAVL<K, V>> sameKeyNodes) {
        this.sameKeyNodes = sameKeyNodes;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public NodeAVL<K, V> getLeft() {
        return left;
    }

    public void setLeft(NodeAVL<K, V> left) {
        this.left = left;
    }

    public NodeAVL<K, V> getRight() {
        return right;
    }

    public void setRight(NodeAVL<K, V> right) {
        this.right = right;
    }

    public NodeAVL<K, V> getParent() {
        return parent;
    }

    public void setParent(NodeAVL<K, V> parent) {
        this.parent = parent;
    }
    
    @Override
    public int compareTo(K k) {
        return key.compareTo(k);
    }
}
