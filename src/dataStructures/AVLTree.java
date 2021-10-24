
package dataStructures;

import java.util.ArrayList;

public class AVLTree  <K extends Comparable<K>,V> implements IAVLTree<K,V>{

    private NodeAVL<K,V> root;

    public AVLTree(NodeAVL<K, V> root) {
        root=null;
    }

    public NodeAVL<K, V> getRoot() {
        return root;
    }

    public void setRoot(NodeAVL<K, V> root) {
        this.root = root;
    }
    
    public NodeAVL<K,V>  privateSearch(K key) {
        return search(root,key);
    }

    @Override
    public NodeAVL<K,V> search(NodeAVL<K,V> node,K key) {
        if(node==null || node.compareTo(key)==0) {
            return node;
        }
        else {
            if(node.compareTo(key)<0) {
                return search(node.getLeft(), key);
            }
            else{
                return search(node.getRight(), key);
            }
        }
    }

    @Override
    public boolean insert(K key, V value) {
        NodeAVL<K,V> newNode = new NodeAVL<>(key,value);
        boolean inserted = false;
        if(root==null) {
            root = newNode;
        }else {
            insertNodeRecursive(root, newNode);
            inserted = true;
        }
        return inserted;
    }
    
    private void insertNodeRecursive( NodeAVL<K,V> node,NodeAVL<K,V> newNode) {
        if(newNode.compareTo(node.getKey())<0){
            if(node.getLeft()==null){
                node.setLeft(newNode);
                newNode.setParent(node);
            }
            else{
                insertNodeRecursive(node.getLeft(),newNode);
            }
        }
        else{
            if(node.getRight()==null){
                node.setRight(newNode);
                newNode.setParent(node);
            }
            else{
                insertNodeRecursive(node.getRight(),newNode);
            }	
        }
    }

    @Override
    public void deleted(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateLeft(NodeAVL<K,V> node) {
        node.getRight().setLeft(node);
        node.setRight(node.getRight().getLeft());
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
        node.getRight().setHeight(Math.max(height(node.getRight().getLeft()), height(node.getRight().getRight())) + 1);
        
    }

    @Override
    public void rotateRight(NodeAVL<K, V> node) {
        node.getLeft().setRight(node);
        node.setLeft(node.getLeft().getRight());
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        node.getLeft().setHeight(Math.max(height(node.getLeft().getLeft()), height(node.getLeft().getRight())) + 1);
    }

    @Override
    public int height(NodeAVL<K, V> node) {
        if (node == null){
            return 0;
        }
        else {
            int l = height(node.getLeft());
            int r = height(node.getRight());
            if (l>r) {
                return (l+1);
            } 
            else {
                return (r+1);
            }
        }
    }
    
    public void balance(NodeAVL<K, V> node){
        if (GetBalancingFactor(node)>1 && node.getKey().compareTo(node.getLeft().getKey())<0){
            rotateRight(node);
        }
        if (GetBalancingFactor(node)<-1 && node.getKey().compareTo(node.getRight().getKey())>0){
            rotateLeft(node);
        }
        if (GetBalancingFactor(node)>1 && node.getKey().compareTo(node.getLeft().getKey())>0){
            rotateLeft(node.getLeft());
            rotateRight(node);
        }
        if (GetBalancingFactor(node)<-1 && node.getKey().compareTo(node.getRight().getKey())<0) {
            rotateRight(node.getRight());
            rotateLeft(node);
        }
    }

    @Override
    public int GetBalancingFactor(NodeAVL<K, V> node) {
        if(node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
        
    }

    @Override
    public ArrayList<V> showTree() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
