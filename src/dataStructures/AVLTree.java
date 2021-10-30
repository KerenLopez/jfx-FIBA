
package dataStructures;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> implements IAVLTree<K,V>{
    
    private NodeAVL<K,V> root;

    public AVLTree() {
        root = null;
    }

    public NodeAVL<K, V> getRoot() {
        return root;
    }

    public void setRoot(NodeAVL<K, V> root) {
        this.root = root;
    }

    @Override
    public NodeAVL<K,V> search(K key) {
        NodeAVL<K,V> founded=privateSearch(root,key);
        return founded;
    }
    
    private NodeAVL<K,V> privateSearch(NodeAVL<K,V> node, K key) {
        if(node==null) {
            return null;
        }
        else if(node.compareTo(key)==0){
            return node;
        }
        else {
            if(node.compareTo(key)<0) {
                return privateSearch(node.getLeft(),key);
            }
            else{
                return privateSearch(node.getRight(),key);
            }
        }
    }
    
    @Override
    public boolean insert(K key, V value) {
        NodeAVL<K,V> newNode = new NodeAVL<>(key,value);
        boolean inserted = false;
        if(root==null) {
            root = newNode;
        }
        else {
            privateInsert(root,key,value);
            inserted = true;
        }
        return inserted;
    }
    
    private void privateInsert(NodeAVL<K,V> currentNode, K key, V value) {
        
        /*if (currentNode==null) {
            return (new NodeAVL<>(key,value));
        }*/
        
        if(key.compareTo(currentNode.getKey())<0 && currentNode.getLeft()==null){
            NodeAVL<K,V> newNode = new NodeAVL<>(key, value);
            currentNode.setLeft(newNode);
            newNode.setParent(currentNode);
            reBalance(newNode);
        }
        else if(key.compareTo(currentNode.getKey())<0 && currentNode.getLeft()!=null){
            privateInsert(currentNode.getLeft(),key,value);
        }
        else if(key.compareTo(currentNode.getKey())>0 && currentNode.getRight()==null){
            NodeAVL<K,V> newNode = new NodeAVL<>(key, value);
            currentNode.setRight(newNode);
            newNode.setParent(currentNode);
            reBalance(newNode);
        }
        else if (key.compareTo(currentNode.getKey())>0 && currentNode.getRight()!=null) {
            privateInsert(currentNode.getRight(),key,value);
        }
    }

    @Override
    public boolean deleted(K key) {
        Boolean deleted = false;
        NodeAVL<K,V> founded = search(key);
        if(founded!=null) {
                deletedPrivate(root,key);
                deleted = true;
        }
        return deleted;
    }
    
    private NodeAVL<K, V> deletedPrivate(NodeAVL<K,V> currentNode, K key){
        if (currentNode==null){
            return currentNode;
        }
 
        if (key.compareTo(currentNode.getKey())<0){
            currentNode.setLeft(deletedPrivate(currentNode.getLeft(),key));
        }
        
        else if (key.compareTo(currentNode.getKey())>0){
            currentNode.setRight(deletedPrivate(currentNode.getRight(),key));
        }
        
        else {
            
            if(currentNode.getLeft()==null){
                return currentNode.getRight();
            }
            
            else if(currentNode.getRight()==null){
                currentNode.getLeft();
            }
               
            NodeAVL<K, V> temp = getNodoConValorMaximo(currentNode.getLeft());
            currentNode.setKey(temp.getKey());
            currentNode.setValue(temp.getValue());
            currentNode.setLeft(deletedPrivate(currentNode.getLeft(),temp.getKey()));
            
            
            currentNode.setHeight(1+Math.max(height(currentNode.getLeft()),height(currentNode.getRight())));
 
            int fe=GetBalancingFactor(currentNode);

            if (fe>1 && GetBalancingFactor(currentNode.getLeft())>=0) {
                rotateRight(currentNode);
            }

            if (fe<-1 && GetBalancingFactor(currentNode.getRight())<=0) {
                rotateLeft(currentNode);
            }

            if (fe>1 && GetBalancingFactor(currentNode.getLeft())<0) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
                rotateRight(currentNode);
            }

            if (fe<-1 && GetBalancingFactor(currentNode.getRight())>0) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
                rotateLeft(currentNode);
            }
        }
        return currentNode;
    }

    @Override
    public NodeAVL<K, V> rotateLeft(NodeAVL<K, V> node) {
        NodeAVL<K, V>  nuevaRaiz = node.getRight();
        NodeAVL<K, V> temp = nuevaRaiz.getLeft();
 
        nuevaRaiz.setLeft(node);
        node.setRight(temp);
 
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        nuevaRaiz.setHeight(Math.max(height(nuevaRaiz.getLeft()),height(nuevaRaiz.getRight())) + 1);
        
        return nuevaRaiz;
    }

    @Override
    public NodeAVL<K, V> rotateRight(NodeAVL<K, V> node) {
        NodeAVL<K, V>  nuevaRaiz = node.getLeft();
        NodeAVL<K, V> temp = nuevaRaiz.getRight();
 
        nuevaRaiz.setRight(node);
        node.setLeft(temp);
 
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        nuevaRaiz.setHeight(Math.max(height(nuevaRaiz.getLeft()),height(nuevaRaiz.getRight())) + 1);
        
        return nuevaRaiz;
    }

    @Override
    public int height(NodeAVL<K, V> node) {
        if (node == null){
            return 0;
        }
        else {
            return node.getHeight();
        }
    }

    @Override
    public int GetBalancingFactor(NodeAVL<K, V> node) {
        if(node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
    
     private void reBalance(NodeAVL<K, V> node) {
        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));
 
        int fe=GetBalancingFactor(node);
        
        if (fe>1 && node.getKey().compareTo(node.getLeft().getKey())<0) {
            rotateRight(node);
        }
 
        if (fe<-1 && node.getKey().compareTo(node.getRight().getKey())>0) {
            rotateLeft(node);
        }
 
        if (fe>1 && node.getKey().compareTo(node.getLeft().getKey())>0) {
            node.setLeft(rotateLeft(node.getLeft()));
            rotateRight(node);
        }
 
        if (fe<-1 && node.getKey().compareTo(node.getRight().getKey())<0) {
            node.setRight(rotateRight(node.getRight()));
            rotateLeft(node);
        }
        if(node.getParent()!=null){
            reBalance(node.getParent());
        }
    }
    
    private NodeAVL<K, V> getNodoConValorMaximo(NodeAVL<K, V> node) {
        NodeAVL<K, V> current = node;
        
        while (current.getRight()!=null){
           current = current.getRight();
        }
        return current;
    }

    @Override
    public ArrayList<NodeAVL<K, V>> preOrder() {
        ArrayList<NodeAVL<K,V>> nodes = new ArrayList<>();
        if(root!=null) {
            preOrder(root, nodes);
        }
        return nodes;
    }
    
    private void preOrder(NodeAVL<K,V> node, ArrayList<NodeAVL<K,V>> nodes) {
        if (node!=null) {
            nodes.add(node);
            preOrder(node.getLeft(), nodes);
            preOrder(node.getRight(),nodes);
        }
    }
    
    
}
