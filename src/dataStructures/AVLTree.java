
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
            if(node.compareTo(key)>=0) {
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
        if(key.compareTo(currentNode.getKey())<0 && currentNode.getLeft()==null){
            NodeAVL<K,V> newNode = new NodeAVL<>(key, value);
            currentNode.setLeft(newNode);
            newNode.setParent(currentNode);
            reBalance(newNode, newNode);
        }
        else if(key.compareTo(currentNode.getKey())<0 && currentNode.getLeft()!=null){
            privateInsert(currentNode.getLeft(),key,value);
        }
        else if(key.compareTo(currentNode.getKey())>0 && currentNode.getRight()==null){
            NodeAVL<K,V> newNode = new NodeAVL<>(key, value);
            currentNode.setRight(newNode);
            newNode.setParent(currentNode);
            reBalance(newNode, newNode);
        }
        else if (key.compareTo(currentNode.getKey())>0 && currentNode.getRight()!=null) {
            privateInsert(currentNode.getRight(),key,value);
        }
    }

    @Override
    public boolean delete(K key) {
        Boolean delete = false;
        NodeAVL<K,V> founded = search(key);
        if(founded!=null) {
            deletePrivate(root,key);
            delete = true;
        }
        return delete;
    }
    
    private NodeAVL<K, V> deletePrivate(NodeAVL<K,V> currentNode, K key){
        if (currentNode==null){
            return currentNode;
        }
 
        if (key.compareTo(currentNode.getKey())<0){
            currentNode.setLeft(deletePrivate(currentNode.getLeft(),key));
        }
        
        else if (key.compareTo(currentNode.getKey())>0){
            currentNode.setRight(deletePrivate(currentNode.getRight(),key));
        }
        
        else {
            
            if(currentNode.getLeft()==null){
                return currentNode.getRight();
            }
            
            else if(currentNode.getRight()==null){
                currentNode.getLeft();
            }
               
            NodeAVL<K, V> temp = getnodeMaximumValue(currentNode.getLeft());
            currentNode.setKey(temp.getKey());
            currentNode.setValue(temp.getValue());
            currentNode.setLeft(deletePrivate(currentNode.getLeft(),temp.getKey()));
            
            currentNode.setHeight(1+Math.max(getHeight(currentNode.getLeft()),getHeight(currentNode.getRight())));
 
            int fe=getBalancingFactor(currentNode);

            if (fe>1 && getBalancingFactor(currentNode.getLeft())>=0) {
                rightRotate(currentNode);
            }

            if (fe<-1 && getBalancingFactor(currentNode.getRight())<=0) {
                leftRotate(currentNode);
            }

            if (fe>1 && getBalancingFactor(currentNode.getLeft())<0) {
                currentNode.setLeft(leftRotate(currentNode.getLeft()));
                rightRotate(currentNode);
            }

            if (fe<-1 && getBalancingFactor(currentNode.getRight())>0) {
                currentNode.setRight(rightRotate(currentNode.getRight()));
                leftRotate(currentNode);
            }
        }
        return currentNode;
    }

    @Override
    public NodeAVL<K, V> leftRotate(NodeAVL<K, V> node) {
        NodeAVL<K, V>  newRoot = node.getRight();
        NodeAVL<K, V> temp = newRoot.getLeft();
 
        newRoot.setLeft(node);
        node.setRight(temp);
        
        if (temp != null) {
            temp.setParent(node);
        }
        if (node.getParent() == null) {
            root = newRoot;
        } 
        else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(newRoot);
        } 
        else {
            node.getParent().setRight(newRoot);
        }

        if(newRoot!=null) {
            newRoot.setParent(node.getParent());
        }
        node.setParent(newRoot);
 
        node.setHeight(Math.max(getHeight(node.getLeft()),getHeight(node.getRight())) + 1);
        newRoot.setHeight(Math.max(getHeight(newRoot.getLeft()),getHeight(newRoot.getRight())) + 1);
        
        if(newRoot.getParent()!=null) {
            newRoot.getParent().setHeight(Math.max(getHeight( newRoot.getParent().getLeft()),getHeight( newRoot.getParent().getRight())) + 1);
        }
        
        return newRoot;
    }

    @Override
    public NodeAVL<K, V> rightRotate(NodeAVL<K, V> node) {
        NodeAVL<K, V>  newRoot = node.getLeft();
        NodeAVL<K, V> temp = newRoot.getRight();
 
        newRoot.setRight(node);
        node.setLeft(temp);
        
        if (temp != null) {
            temp.setParent(node);
        }

        if (node.getParent() == null) {
            root = newRoot;
        } 
        else if (node == node.getParent().getRight()) {
            node.getParent().setRight(newRoot);
        } 
        else {
            node.getParent().setLeft(newRoot);
        }

        if(newRoot!=null) {
            newRoot.setParent(node.getParent());
        }
        node.setParent(newRoot);
 
        node.setHeight(Math.max(getHeight(node.getLeft()),getHeight(node.getRight())) + 1);
        newRoot.setHeight(Math.max(getHeight(newRoot.getLeft()),getHeight(newRoot.getRight())) + 1);
        
        if(newRoot.getParent()!=null) {
            newRoot.getParent().setHeight(Math.max(getHeight( newRoot.getParent().getLeft()),getHeight( newRoot.getParent().getRight())) + 1);
        }
        
        return newRoot;
    }

    public int getHeight(NodeAVL<K, V> node) {
        if (node == null){
            return 0;
        }
        else {
            return node.getHeight();
        }
    }

    public int getBalancingFactor(NodeAVL<K, V> node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    
    public void reBalance(NodeAVL<K, V> node, NodeAVL<K, V> nodeKey) {
        node.setHeight(1+Math.max(getHeight(node.getLeft()),getHeight(node.getRight())));
 
        int fe=getBalancingFactor(node);
        
        if (fe>1 && nodeKey.getKey().compareTo(node.getLeft().getKey())<0) {
            rightRotate(node);
        }else if (fe<-1 && nodeKey.getKey().compareTo(node.getRight().getKey())>0) {
            leftRotate(node);
        }else if (fe>1 && nodeKey.getKey().compareTo(node.getLeft().getKey())>0) {
            leftRotate(node.getLeft());
            rightRotate(node);
        }else if (fe<-1 && nodeKey.getKey().compareTo(node.getRight().getKey())<0) {
            rightRotate(node.getRight());
            leftRotate(node);
        }
        
        if(node.getParent()!=null){
            reBalance(node.getParent(), nodeKey);
        }
    }
    
    public NodeAVL<K, V> getnodeMaximumValue(NodeAVL<K, V> node) {
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
