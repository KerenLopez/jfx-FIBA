
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

    @Override
    public NodeAVL<K,V> search(K key) {
        NodeAVL<K,V> founded=privateSearch(root,key);
        return founded;
    }
    
    public NodeAVL<K,V> privateSearch(NodeAVL<K,V> node, K key) {
        if(node==null || node.compareTo(key)==0) {
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
        }else {
            privateInsert(root, newNode);
            inserted = true;
        }
        return inserted;
    }
    
    private void privateInsert( NodeAVL<K,V> node,NodeAVL<K,V> newNode) {
        if(newNode.compareTo(node.getKey())<=0){
            if(node.getLeft()==null){
                node.setLeft(newNode);
                newNode.setParent(node);
            }
            else{
                privateInsert(node.getLeft(),newNode);
            }
        }
        else{
            if(node.getRight()==null){
                node.setRight(newNode);
                newNode.setParent(node);
            }
            else{
                privateInsert(node.getRight(),newNode);
            }	
        }
        newNode.setHeight(Math.max(height(newNode.getLeft()), height(newNode.getRight()))+1);
        balance(newNode);
    }

    @Override
    public boolean deleted(K key) {
        Boolean deleted = false;
        NodeAVL<K,V> founded = search(key);
        if(founded!=null) {
                deletedPrivate(founded);
                deleted = true;
        }
        return deleted;
    }
    
    private void deletedPrivate(NodeAVL<K,V> node){
        if(node.getLeft()==null && node.getRight()==null){
            if(node==root){
                root=null;
            }
            else if(node==node.getParent().getLeft()){
                node.getParent().setLeft(null);		
            }
            else{
                node.getParent().setRight(null);;
            }
            node.setParent(null);
        }
        
        else if(node.getLeft()==null || node.getRight()==null){
            NodeAVL<K,V> onlyChild;
            if(node.getLeft()!=null){
                onlyChild=node.getLeft();
                node.setLeft(null);
            }
            else{
                onlyChild=node.getRight();
                node.setRight(null);
            }
            
            onlyChild.setParent(node.getParent());
            
            if(node==root){
                root=onlyChild;
            }
            else if(node==node.getParent().getLeft()){
                node.getParent().setLeft(onlyChild);	
            }
            else{
                node.getParent().setRight(onlyChild);
            }
            node.setParent(null);
        }
        
        else{
            NodeAVL<K,V> sucesor=minValueNode(node.getRight());
            node.setValue(sucesor.getValue());
            deletedPrivate(sucesor);
        }
        
        root.setHeight(Math.max(height(root.getLeft()),height(root.getRight()))+1);
        int balance=GetBalancingFactor(root);
        
        if (balance>1 && GetBalancingFactor(root.getLeft())>=0){
            rotateRight(root);
        }
        if (balance>1 && GetBalancingFactor(root.getLeft())<0) {
            root.setLeft(rotateLeft(root.getLeft()));
            rotateRight(root);
        }
        if (balance < -1 && GetBalancingFactor(root.getRight()) <= 0){
            rotateLeft(root);
        }
        if (balance<-1 && GetBalancingFactor(root.getRight()) > 0) {
            root.setRight(rotateRight((NodeAVL<K,V>) root.getRight()));
            rotateLeft(root);
        }
    }

    @Override
    public NodeAVL<K,V> rotateLeft(NodeAVL<K,V> node) {
        node.getRight().setLeft(node);
        node.setRight(node.getRight().getLeft());
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
        node.getRight().setHeight(Math.max(height(node.getRight().getLeft()), height(node.getRight().getRight())) + 1);
        return node.getLeft();
    }

    @Override
    public NodeAVL<K,V> rotateRight(NodeAVL<K, V> node) {
        node.getLeft().setRight(node);
        node.setLeft(node.getLeft().getRight());
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        node.getLeft().setHeight(Math.max(height(node.getLeft().getLeft()), height(node.getLeft().getRight())) + 1);
        return node.getRight();
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
    
    public NodeAVL<K, V> minValueNode(NodeAVL<K, V> node){
        while(node.getLeft()!=null){
        node=(NodeAVL<K, V>)node.getLeft();
        }
        return node;
    }
    
    public String preOrder(NodeAVL<K,V> node) {
        String k="";
        if (node==null) {
            return k;
        }
        else{
            k+=node.getKey()+" ";
            k+=preOrder(node.getLeft());
            k+=preOrder(node.getRight());
        }
        return k;
    }
    
    
}
